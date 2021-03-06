package it.iot.server;

import com.mongodb.client.MongoDatabase;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import it.iot.server.Helper.MqttConnection;
import it.iot.server.Http.ServerFacade;
import it.iot.server.DataMapper.MongoDb.Measure;
import it.iot.server.Helper.MongoDbConnection;
import it.iot.server.Ingestor.Concurrent;
import it.iot.server.Ingestor.Mqtt;
import it.iot.server.Storage.Database;

import java.io.ObjectInputStream;

import it.iot.server.Recommender.RecommenderInterface;
import it.iot.server.Recommender.SimpleReflex;
import it.iot.server.Recommender.StateReader;
import it.iot.server.Recommender.Classifier.ClassifierInterface;
import it.iot.server.Recommender.Classifier.Cart.*;

/**
 * IoT connector application
 * 
 * @author Marco Zanella
 */
public class App {
    /**
     * Program entry point
     * 
     * Reads configuration, issues connections to necessary services
     * (database, MQTT broker...), builds a recommender, then waits
     * for new measures to insert, measure search requests, and
     * periodically sends recommendations.
     * 
     * @param args Argument vector, first element must be path to
     *             configuration file
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Arguments: <config path>");
            return;
        }
        PropertyConfigurator.configure(args[0]);

        try {
            // Reads configuration
            Properties configuration = new Properties();
            configuration.load(new FileInputStream(args[0]));

            // Starts
            Logger.getLogger("standard").info("Running with process id: " + ProcessHandle.current().pid());

            // Builds storage
            MongoDatabase database = MongoDbConnection.fromParameters(
                configuration.getProperty("mongodb.host"),
                Integer.parseInt(configuration.getProperty("mongodb.port")),
                configuration.getProperty("mongodb.name")
            ).asDatabase();
            Measure mapper = new Measure(database);
            Database storage = new Database(mapper);

            // Builds MQTT client
            IMqttClient client = MqttConnection.fromParameters(
                configuration.getProperty("mqtt.protocol"),
                configuration.getProperty("mqtt.host"),
                Integer.parseInt(configuration.getProperty("mqtt.port")),
                configuration.getProperty("mqtt.id"),
                configuration.getProperty("mqtt.username"),
                configuration.getProperty("mqtt.password")
            ).connect();

            // Builds ingestor
            Mqtt mqtt_ingestor = new Mqtt(client, storage);
            Concurrent ingestor = new Concurrent(mqtt_ingestor);
            ingestor.activate();

            // Builds web server
            ServerFacade httpServer = new ServerFacade(
                Integer.parseInt(configuration.getProperty("http.port")),
                mapper
            );
            httpServer.start();

            // Builds recommender
            FileInputStream fileInputStream = new FileInputStream(configuration.getProperty("recommender.path"));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ClassifierInterface node = (Split) objectInputStream.readObject();
            objectInputStream.close();
            RecommenderInterface simpleReflex = new SimpleReflex(
                new StateReader(configuration.getProperty("recommender.roomIdentifier"), mapper),
                node,
                new it.iot.server.Recommender.Mqtt(client)
            );
            RecommenderInterface concurrent = new it.iot.server.Recommender.Concurrent(
                simpleReflex,
                Integer.parseInt(configuration.getProperty("recommender.sleep"))
            );
            RecommenderInterface recommender = concurrent;
            recommender.activate();

            // Waits for SIGTERM...
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    Logger.getLogger("standard").info("Received termination signal.");
                    ingestor.stop();
                    httpServer.stop();
                    recommender.stop();
                    try {
                        client.disconnect();
                        client.close();
                        Logger.getLogger("standard").info("MQTT client disconnected.");
                    } catch (MqttException e) {
                        Logger.getLogger("standard").error(e.getMessage());
                        Logger.getLogger("standard").debug(ExceptionUtils.getStackTrace(e));
                    }
                }
            });
        } catch (Exception e) {
            Logger.getLogger("default").fatal(e.getMessage());
            Logger.getLogger("default").debug(ExceptionUtils.getStackTrace(e));
        }
    }
}
