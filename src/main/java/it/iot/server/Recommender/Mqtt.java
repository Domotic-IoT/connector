package it.iot.server.Recommender;

import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttClient;

public class Mqtt implements ActionPerformerInterface {
    private IMqttClient client;
    private Logger logger;

    public Mqtt(IMqttClient client, Logger logger) {
        this.client = client;
        this.logger = logger;
    }

    public Mqtt(IMqttClient client) {
        this.client = client;
        this.logger = Logger.getLogger("default");
    }

    public IMqttClient getClient() {
        return client;
    }

    public Logger getLogger() {
        return logger;
    }

    /**
     * @todo Implement me
     */
    @Override
    public void write(String action) {
        logger.info("Performing action over MQTT : " + action + ".");
    }
}
