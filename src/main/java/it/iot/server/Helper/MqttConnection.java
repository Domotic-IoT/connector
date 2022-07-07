package it.iot.server.Helper;

import javax.net.ssl.SSLSocketFactory;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttConnection {
    private String protocol;
    private String host;
    private int port;
    private String id;
    private String username;
    private String password;

    public MqttConnection(
        String protocol,
        String host,
        int port,
        String id,
        String username,
        String password
    ) {
        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public static MqttConnection fromParameters(
        String protocol,
        String host,
        int port,
        String id,
        String username,
        String password
    ) {
        return new MqttConnection(protocol, host, port, id, username, password);
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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUri() {
        return protocol + "://" + host + ":" + port;
    }

    public MqttClient connect() throws MqttException {
        MqttClient client = new MqttClient(getUri(), id);
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        if (!username.isEmpty() && !password.isEmpty()) {
            options.setUserName(username);
            options.setPassword(password.toCharArray());
            options.setSocketFactory(SSLSocketFactory.getDefault());
        }
        client.connect(options);
        return client;
    }
}
