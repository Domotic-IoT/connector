package it.iot.server.Helper;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 * Helper class for connecting to MongoDB
 * 
 * This class implements the Static Factory Method pattern.
 * 
 * @author Marco Zanella
 */
public class MongoDbConnection {
    /**
     * Database host
     */
    private String host;

    /**
     * Database port
     */
    private int port;

    /**
     * Database name
     */
    private String databaseName;

    /**
     * Constructor
     * 
     * @param host         Database host
     * @param port         Database port
     * @param databaseName Database name
     */
    public MongoDbConnection(String host, int port, String databaseName) {
        this.host = host;
        this.port = port;
        this.databaseName = databaseName;
    }

    /**
     * Builds a connection helper from its parameters
     * 
     * This is a Static Factory Method
     * 
     * @param host         Database host
     * @param port         Database port
     * @param databaseName Database name
     * @return A new connection helper
     */
    public static MongoDbConnection fromParameters(String host, int port, String databaseName) {
        return new MongoDbConnection(host, port, databaseName);
    }

    /**
     * Returns database host
     * 
     * @return Database host
     */
    public String getHost() {
        return host;
    }

    /**
     * Returns database port
     * 
     * @return Database port
     */
    public int getPort() {
        return port;
    }

    /**
     * Returns database name
     * 
     * @return Database name
     */
    public String getDatabaseName() {
        return databaseName;
    }

    /**
     * Returns connection URI
     * 
     * @return Connection URI
     */
    public String getUri() {
        return "mongodb://" + host + ":" + port;
    }

    /**
     * Returns connection
     * 
     * @return Connection
     */
    public MongoDatabase asDatabase() {
        MongoClient client = MongoClients.create(getUri());
        return client.getDatabase(databaseName);
    }
}
