package it.iot.server.Helper;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttConnection {
    private String protocol;
    private String host;
    private int port;
    private String id;

    public MqttConnection(String protocol, String host, int port, String id) {
        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.id = id;
    }

    public static MqttConnection fromParameters(String protocol, String host, int port, String id) {
        return new MqttConnection(protocol, host, port, id);
    }

    public String getProtocol() {
        return protocol;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getId() {
        return id;
    }

    public String getUri() {
        return protocol + "://" + host + ":" + port;
    }

    public MqttClient asClient() throws MqttException {
        return new MqttClient(getUri(), id);
    }
}
