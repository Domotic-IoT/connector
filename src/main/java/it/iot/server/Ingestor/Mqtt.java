package it.iot.server.Ingestor;

import java.nio.charset.StandardCharsets;
import java.rmi.UnexpectedException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import it.iot.server.Measure.AbstractMeasure;
import it.iot.server.Measure.FineDust;
import it.iot.server.Measure.LightLevel;
import it.iot.server.Measure.HeatIndex;
import it.iot.server.Measure.Humidity;
import it.iot.server.Measure.Temperature;
import it.iot.server.Storage.StorageInterface;

/**
 * Ingestor based on MQTT
 * 
 * Subscribes to one or more topics on an MQTT broker, then continuosly
 * waits for new messages. Inserts measures in a storage medium as soon
 * as they arrive.
 * 
 * @author Marco Zanella
 */
public class Mqtt implements IngestorInterface {
    /**
     * Sleeping time
     */
    public static final int WAITING_TIME = 1000;

    /**
     * MQTT client
     */
    private IMqttClient client;

    /**
     * Storage medium
     */
    private StorageInterface storage;

    /**
     * Logger
     */
    private Logger logger;

    /**
     * Constructor
     * 
     * @param client  MQTT client
     * @param storage Storage medium
     * @param logger  Logger
     */
    public Mqtt(
        IMqttClient client,
        StorageInterface storage,
        Logger logger
    ) {
        this.client = client;
        this.storage = storage;
        this.logger = logger;
    }

    /**
     * Constructor
     * 
     * Uses default logger.
     * 
     * @param client  MQTT client
     * @param storage Storage medium
     */
    public Mqtt(
        IMqttClient client,
        StorageInterface storage
    ) {
        this.client = client;
        this.storage = storage;
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
     * Returns storage medium
     * 
     * @return Storage medium
     */
    public StorageInterface getStorage() {
        return storage;
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
     * Activates ingestor
     */
    @Override
    public void activate() {
        this.logger.info("MQTT ingestor activated.");
        try {
            client.subscribe("sensors/#", (topic, message) -> processMessage(topic, message));
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(WAITING_TIME);
            }
        } catch (MqttException e) {
            logger.error(e.getMessage());
            logger.debug(ExceptionUtils.getStackTrace(e));
        } catch (InterruptedException e) {
            // Do nothing
        }
    }

    /**
     * Stops ingestor
     */
    @Override
    public void stop() {
        logger.info("MQTT ingestor stopped.");
    }

    /**
     * Processes an incoming message
     * 
     * Converts message into a measure, then stores it into storage medium.
     * 
     * @param topic   MQTT topic
     * @param message MQTT message
     */
    private void processMessage(String topic, MqttMessage message) {
        String roomId = topic.replaceAll("^sensors/(\\w*)/(\\w*)", "$1");
        String type = topic.replaceAll("^sensors/(\\w*)/(\\w*)", "$2");
        byte[] payload = message.getPayload();
        String content = new String(payload, StandardCharsets.UTF_8);
        logger.info("Received message from topic \"" + topic + "\": " + content + ".");

        ObjectMapper mapper = new ObjectMapper();
        try {
            MqttMeasure rawData = mapper.readValue(content, MqttMeasure.class);
            logger.info("Parsed message from " + rawData.getDeviceIdentifier() + ".");
            AbstractMeasure measure = processMeasure(roomId, type, rawData);
            storage.persist(measure);
        }
        catch (JsonProcessingException e) {
            logger.error("Could not parse message: " + e.getMessage());
            logger.debug(ExceptionUtils.getStackTrace(e));
        }
        catch (UnexpectedException e) {
            logger.error("Could not parse message: " + e.getMessage());
            logger.debug(ExceptionUtils.getStackTrace(e));
        }
    }

    /**
     * Converts raw data into a measure
     * @param roomIdentifier Identifier of room
     * @param type           Type of measure
     * @param rawData        Raw data
     * @return Measure
     * @throws UnexpectedException If type of measure is unknown
     */
    private AbstractMeasure processMeasure(String roomIdentifier, String type, MqttMeasure rawData) throws UnexpectedException {
        switch (type) {
            case "temperature":
                return new Temperature(
                    roomIdentifier,
                    rawData.getDeviceIdentifier(),
                    rawData.getValue(),
                    rawData.getAbsoluteError(),
                    (int) java.time.Instant.now().getEpochSecond()
                );
            case "humidity":
                return new Humidity(
                    roomIdentifier,
                    rawData.getDeviceIdentifier(),
                    rawData.getValue(),
                    rawData.getAbsoluteError(),
                    (int) java.time.Instant.now().getEpochSecond()
                );
            case "heatIndex":
                return new HeatIndex(
                    roomIdentifier,
                    rawData.getDeviceIdentifier(),
                    rawData.getValue(),
                    rawData.getAbsoluteError(),
                    (int) java.time.Instant.now().getEpochSecond()
                );
            case "lightLevel":
                return new LightLevel(
                    roomIdentifier,
                    rawData.getDeviceIdentifier(),
                    rawData.getValue(),
                    rawData.getAbsoluteError(),
                    (int) java.time.Instant.now().getEpochSecond()
                );
            case "fineDust":
                return new FineDust(
                    roomIdentifier,
                    rawData.getDeviceIdentifier(),
                    rawData.getValue(),
                    rawData.getAbsoluteError(),
                    (int) java.time.Instant.now().getEpochSecond()
                );
            default:
                throw new UnexpectedException("Unknown type \"" + type + "\".");
        }
    }
}
