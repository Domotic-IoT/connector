package it.iot.server.Helper;

import javax.net.ssl.SSLSocketFactory;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * Connection helper for MQTT brokers
 * 
 * This class implements the Static Factory Method pattern.
 * 
 * @author Marco Zanella
 */
public class MqttConnection {
    /**
     * Broker protocol
     */
    private String protocol;

    /**
     * Broker host
     */
    private String host;

    /**
     * Broker port
     */
    private int port;

    /**
     * Client identifier
     */
    private String id;

    /**
     * Username
     */
    private String username;

    /**
     * Password
     */
    private String password;

    /**
     * Constructor
     * 
     * @param protocol Broker protocol
     * @param host     Broker host
     * @param port     Broker port
     * @param id       Client identifier
     * @param username Username
     * @param password Password
     */
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

    /**
     * Builds a MQTT helper from its parameters
     * 
     * @param protocol Broker protocol
     * @param host     Broker host
     * @param port     Broker port
     * @param id       Client identifier
     * @param username Username
     * @param password Password
     * @return An MQTT connection helper
     */
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

    /**
     * Returns broker protocol
     * 
     * @return Broker protocol
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * Returns broker host
     * @return Broker host
     */
    public String getHost() {
        return host;
    }

    /**
     * Returns broker port
     * @return Broker port
     */
    public int getPort() {
        return port;
    }

    /**
     * Returns broker identifier
     * @return Broker identifier
     */
    public String getId() {
        return id;
    }

    /**
     * Returns username
     * @return Username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns password
     * @return Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns connection URI
     * 
     * @return Connection URI
     */
    public String getUri() {
        return protocol + "://" + host + ":" + port;
    }

    /**
     * Connects to broker and returns client
     * 
     * @return Client
     * @throws MqttException In case of connection issues
     */
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
