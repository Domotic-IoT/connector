package it.iot.server.Ingestor;

import java.nio.charset.StandardCharsets;
import java.rmi.UnexpectedException;

import javax.net.ssl.SSLSocketFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import it.iot.server.Measure.AbstractMeasure;
import it.iot.server.Measure.FineDust;
import it.iot.server.Measure.LightLevel;
import it.iot.server.Measure.HeatIndex;
import it.iot.server.Measure.Humidity;
import it.iot.server.Measure.Temperature;
import it.iot.server.Storage.StorageInterface;

public class Mqtt implements IngestorInterface {
    public static final int WAITING_TIME = 1000;
    private IMqttClient client;
    private String username;
    private String password;
    private StorageInterface storage;
    private Logger logger;

    public Mqtt(
        IMqttClient client,
        String username,
        String password,
        StorageInterface storage,
        Logger logger
    ) {
        this.client = client;
        this.username = username;
        this.password = password;
        this.storage = storage;
        this.logger = logger;
    }

    public Mqtt(
        IMqttClient client,
        String username,
        String password,
        StorageInterface storage
    ) {
        this.client = client;
        this.username = username;
        this.password = password;
        this.storage = storage;
        this.logger = Logger.getLogger("default");
    }

    public IMqttClient getClient() {
        return client;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public StorageInterface getStorage() {
        return storage;
    }

    public Logger getLogger() {
        return logger;
    }

    @Override
    public void activate() {
        this.logger.info("MQTT ingestor activated.");
        try {
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);
            options.setUserName(username);
            options.setPassword(password.toCharArray());
            options.setSocketFactory(SSLSocketFactory.getDefault());
            client.connect(options);
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

    @Override
    public void stop() {
        logger.info("MQTT ingestor stopped.");
        try {
            client.disconnect();
            client.close();
        } catch (MqttException e) {
            logger.error(e.getMessage());
            logger.debug(ExceptionUtils.getStackTrace(e));
        }
    }

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
