package it.iot.server.Converter.Bson;

import org.apache.log4j.Logger;
import org.bson.Document;

import it.iot.server.Converter.MeasureInterface;
import it.iot.server.Measure.AbstractMeasure;
import it.iot.server.Measure.FineDust;
import it.iot.server.Measure.HeatIndex;
import it.iot.server.Measure.Humidity;
import it.iot.server.Measure.LightLevel;
import it.iot.server.Measure.Temperature;

/**
 * A BSON measure converter.
 * 
 * @author Marco Zanella
 */
public class Measure implements MeasureInterface<Document> {
    /**
     * Visitor to convert a measure into a BSON object
     */
    private MeasureVisitor visitor;

    /**
     * Logger
     */
    private Logger logger;

    /**
     * Constructor
     * 
     * @param visitor Visitor to convert from measure to BSON object
     * @param logger  Logger
     */
    public Measure(MeasureVisitor visitor, Logger logger) {
        this.visitor = visitor;
        this.logger = logger;
    }

    /**
     * Default constructor
     * 
     * Uses a default visitor and the default logger
     */
    public Measure() {
        this.visitor = new MeasureVisitor();
        this.logger = Logger.getLogger("default");
    }

    /**
     * Returns visitor
     * 
     * @return Visitor
     */
    public MeasureVisitor getVisitor() {
        return visitor;
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
     * Converts a measure into a BSON object
     * 
     * @param measure Measure
     * @return BSON object
     */
    @Override
    public Document fromObject(AbstractMeasure measure) {
        measure.accept(visitor);
        return visitor.getDocument();
    }

    /**
     * Converts a BSON object into a measure
     * 
     * @param data BSON object
     * @return Measure
     */
    @Override
    public AbstractMeasure toObject(Document data) {
        String type = data.getString("type");
        String roomIdentifier = data.getString("roomId");
        String deviceIdentifier = data.getString("deviceId");
        double value = data.getDouble("value");
        double absoluteError = data.getDouble("absoluteError");
        int timestamp = data.getInteger("timestamp");

        switch (type) {
            case "temperature":
                return new Temperature(roomIdentifier, deviceIdentifier, value, absoluteError, timestamp);
            case "humidity":
                return new Humidity(roomIdentifier, deviceIdentifier, value, absoluteError, timestamp);
            case "heatIndex":
                return new HeatIndex(roomIdentifier, deviceIdentifier, value, absoluteError, timestamp);
            case "lightLevel":
                return new LightLevel(roomIdentifier, deviceIdentifier, value, absoluteError, timestamp);
            case "fineDust":
                return new FineDust(roomIdentifier, deviceIdentifier, value, absoluteError, timestamp);
            default:
                throw new IllegalArgumentException("Unknown measure type \"" + type + "\".");
        }
    }
}
