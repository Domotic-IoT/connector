package it.iot.server.Helper;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDbConnection {
    private String host;
    private int port;
    private String databaseName;

    public MongoDbConnection(String host, int port, String databaseName) {
        this.host = host;
        this.port = port;
        this.databaseName = databaseName;
    }

    public static MongoDbConnection fromParameters(String host, int port, String databaseName) {
        return new MongoDbConnection(host, port, databaseName);
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getUri() {
        return "mongodb://" + host + ":" + port;
    }

    public MongoDatabase asDatabase() {
        MongoClient client = MongoClients.create(getUri());
        return client.getDatabase(databaseName);
    }
}
