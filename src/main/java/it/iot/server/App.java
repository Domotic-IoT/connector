package it.iot.server;

import com.mongodb.client.MongoDatabase;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.paho.client.mqttv3.IMqttClient;

import it.iot.server.Helper.MqttConnection;
import it.iot.server.Http.ServerFacade;
import it.iot.server.DataMapper.MongoDb.Measure;
import it.iot.server.Helper.MongoDbConnection;
import it.iot.server.Ingestor.Concurrent;
import it.iot.server.Ingestor.Mqtt;
import it.iot.server.Storage.Database;

public class App {
    public static void main( String[] args ) {
        if (args.length < 2) {
            System.out.println("Arguments: <config path>");
            return;
        }
        PropertyConfigurator.configure(args[0]);

        try {
            // Reads configuration
            Properties configuration = new Properties();
            configuration.load(new FileInputStream(args[0]));

            // Builds storage
            MongoDatabase database = MongoDbConnection.fromParameters(
                configuration.getProperty("mongodb.host"),
                Integer.parseInt(configuration.getProperty("mongodb.port")),
                configuration.getProperty("mongodb.name")
            ).asDatabase();
            Measure mapper = new Measure(database);
            Database storage = new Database(mapper);

            // Builds ingestor
            IMqttClient client = MqttConnection.fromParameters(
                configuration.getProperty("mqtt.protocol"),
                configuration.getProperty("mqtt.host"),
                Integer.parseInt(configuration.getProperty("mqtt.port")),
                configuration.getProperty("mqtt.id")
            ).asClient();
            Mqtt mqtt_ingestor = new Mqtt(
                client,
                configuration.getProperty("mqtt.username"),
                configuration.getProperty("mqtt.password"),
                storage
            );
            Concurrent ingestor = new Concurrent(mqtt_ingestor);
            ingestor.activate();

            // Builds web server
            ServerFacade httpServer = new ServerFacade(
                Integer.parseInt(configuration.getProperty("http.port")),
                mapper
            );
            httpServer.start();

            // Waits for signal...
            Logger.getLogger("standard").info("Running with process id: " + ProcessHandle.current().pid());
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    Logger.getLogger("standard").info("Received termination signal.");
                    ingestor.stop();
                    httpServer.stop();
                }
            });
            while (true) {
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            Logger.getLogger("default").fatal(e.getMessage());
            Logger.getLogger("default").debug(ExceptionUtils.getStackTrace(e));
        }
    }
}
