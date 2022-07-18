package it.iot.server.Recommender;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Performs action over an MQTT broker
 * 
 * @author Marco Zanella
 */
public class Mqtt implements ActionPerformerInterface {
    /**
     * MQTT client
     */
    private IMqttClient client;

    /**
     * Logger
     */
    private Logger logger;

    /**
     * Constructor
     * 
     * @param client MQTT client
     * @param logger Logger
     */
    public Mqtt(IMqttClient client, Logger logger) {
        this.client = client;
        this.logger = logger;
    }

    /**
     * Constructor
     * 
     * Uses default logger.
     * 
     * @param client MQTT client
     */
    public Mqtt(IMqttClient client) {
        this.client = client;
        this.logger = Logger.getLogger("default");
    }

    /**
     * Returns MQTT client
     * 
     * @return MQTT client
     */
    public IMqttClient getClient() {
        return client;
    }

    /**
     * Returns logger
     * 
     * @return Logger
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * Writes an action to the MQTT broker
     * 
     * @param action Action to perform
     */
    @Override
    public void write(String action) {
        logger.info("Sending action over MQTT : " + action + ".");
        Pattern pattern = Pattern.compile("(\\p{Alpha}+)\\((\\p{Alpha}+)\\)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(action);
        boolean matchFound = matcher.find();
        if (matchFound) {
            String device = matcher.group(1);
            String state = matcher.group(2);
            MqttMessage message = new MqttMessage(state.getBytes());
            message.setQos(0);
            try {
                client.publish("actuators/" + device, message);
            }
            catch (Exception e) {
                logger.warn("Could not send action: " + e.getMessage() + ".");
            }
        }
        else {
            logger.warn("Unknown action \"" + action + "\".");
        }
    }
}
