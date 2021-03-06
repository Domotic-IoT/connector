package it.iot.server.DataMapper.MongoDb;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.mongodb.client.MongoDatabase;

import it.iot.server.DataMapper.MeasureInterface;
import it.iot.server.DataMapper.QueryInterface;
import it.iot.server.Measure.AbstractMeasure;

/**
 * Measure data mapper for MongoDB
 * 
 * @author Marco Zanella
 */
public class Measure implements MeasureInterface {
    /**
     * MongoDB database
     */
    private MongoDatabase database;

    /**
     * BSON converter
     */
    private it.iot.server.Converter.Bson.Measure converter;

    /**
     * Logger
     */
    private Logger logger;

    /**
     * Constructor
     * 
     * @param database  MongoDB database
     * @param converter BSON converter
     * @param logger    Logger
     */
    public Measure(MongoDatabase database, it.iot.server.Converter.Bson.Measure converter, Logger logger) {
        this.database = database;
        this.converter = converter;
        this.logger = logger;
    }

    /**
     * Constructor
     * 
     * Uses default BSON converter and logger
     * 
     * @param database MongoDB database
     */
    public Measure(MongoDatabase database) {
        this.database = database;
        this.converter = new it.iot.server.Converter.Bson.Measure();
        this.logger = Logger.getLogger("default");
    }

    /**
     * Returns MongoDB database
     * 
     * @return MongoDB database
     */
    public MongoDatabase getDatabase() {
        return database;
    }

    /**
     * Returns BSON converter
     * 
     * @return BSON converter
     */
    public it.iot.server.Converter.Bson.Measure getConverter() {
        return converter;
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
     * Inserts a measure into database
     * 
     * @param measure Measure
     */
    @Override
    public void create(AbstractMeasure measure) {
        database.getCollection("measures").insertOne(converter.fromObject(measure));
        logger.info("Inserted measure into MongoDb.");
    }

    /**
     * Searches for measures
     * 
     * @param query Query object
     * @return List of measures
     */
    @Override
    public Iterable<AbstractMeasure> search(QueryInterface query) {
        logger.info("Searching measures from MongoDb.");
        ArrayList<Document> clauses = new ArrayList<Document>();
        query.getType().ifPresent(type -> clauses.add(new Document("type", type)));
        query.getRoomIdentifier().ifPresent(id -> clauses.add(new Document("roomId", id)));
        query.getDeviceIdentifier().ifPresent(id -> clauses.add(new Document("deviceId", id)));
        query.getFromTimestamp().ifPresent(timestamp -> clauses.add(new Document("timestamp", new Document("$gte", timestamp))));
        query.getToTimestamp().ifPresent(timestamp -> clauses.add(new Document("timestamp", new Document("$lte", timestamp))));
        Document bson_query = new Document("$and", clauses);
        List<Document> results = new ArrayList<>();
        database.getCollection("measures").find(bson_query).into(results);
        ArrayList<AbstractMeasure> measures = new ArrayList<>();
        for (Document result: results) {
            measures.add(converter.toObject(result));
        }
        return measures;
    }
}
