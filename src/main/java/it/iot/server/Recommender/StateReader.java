package it.iot.server.Recommender;

import java.util.Arrays;
import org.apache.log4j.Logger;
import it.iot.server.DataMapper.MeasureInterface;
import it.iot.server.DataMapper.SimpleQuery;
import it.iot.server.Measure.AbstractMeasure;

/**
 * A state reader
 * 
 * Reads current state from a database, then returns it as a point
 * representing temperature, humidity, light level and ozone level.
 * 
 * Values are computed as the average of the last timestamp seconds.
 * If there are no measures available, the value 0.0 is used.
 * 
 * @author Marco Zanella
 */
public class StateReader {
    /**
     * Timestamp for averaging last measures
     */
    public static final int timespan = 30 * 60;

    /**
     * Identifier of room
     */
    private String roomIdentifier;

    /**
     * Measure data mapper
     */
    private MeasureInterface mapper;

    /**
     * Logger
     */
    private Logger logger;

    /**
     * Constructor
     * 
     * @param roomIdentifier Identifier of room
     * @param mapper         Measure data mapper
     * @param logger         Logger
     */
    public StateReader(String roomIdentifier, MeasureInterface mapper, Logger logger) {
        this.roomIdentifier = roomIdentifier;
        this.mapper = mapper;
        this.logger = logger;
    }

    /**
     * Constructor
     * 
     * Uses default logger.
     * 
     * @param roomIdentifier Identifier of room
     * @param mapper         Measure data mapper
     */
    public StateReader(String roomIdentifier, MeasureInterface mapper) {
        this.roomIdentifier = roomIdentifier;
        this.mapper = mapper;
        this.logger = Logger.getLogger("default");
    }

    /**
     * Returns identifier of room
     * 
     * @return Identifier of room
     */
    public String getRoomIdentifier() {
        return roomIdentifier;
    }

    /**
     * Returns measure data mapper
     * 
     * @return Measure data mapper
     */
    public MeasureInterface getMapper() {
        return mapper;
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
     * Reads current state
     * 
     * @return A point representing current state
     */
    public double[] read() {
        double[] state = {
            readMeasure("temperature"),
            readMeasure("humidity"),
            readMeasure("lightLevel"),
            readMeasure("ozone"),
        };
        logger.info("Reading state: " + Arrays.toString(state) + ".");
        return state;
    }

    /**
     * Reads a measure of a given type
     * 
     * @param type Type of measure
     * @return Average of values for measure
     */
    private double readMeasure(String type) {
        double value = 0.0;
        int size = 0;
        int now = (int) java.time.Instant.now().getEpochSecond();
        SimpleQuery query = new SimpleQuery();
        query
            .roomIdentifier(roomIdentifier)
            .fromTimestamp(now - timespan)
            .toTimestamp(now)
            .type(type)
        ;
        for (AbstractMeasure measure : mapper.search(query)) {
            value += measure.getValue();
            size++;
        }
        if (size > 0) {
            value /= size;
        }
        logger.info("Reading " + type + ": " + value + " (average of " + size + " measures).");
        return value;
    }
}
