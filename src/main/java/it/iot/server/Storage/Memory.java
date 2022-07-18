package it.iot.server.Storage;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import it.iot.server.Measure.AbstractMeasure;

/**
 * In-memory storage medium
 * 
 * Data is lost once program terminates.
 * 
 * @author Marco Zanella
 */
public class Memory implements StorageInterface {
    /**
     * List of measures
     */
    private ArrayList<AbstractMeasure> data;

    /**
     * Logger
     */
    private Logger logger;

    /**
     * Constructor
     * @param data   List of measures
     * @param logger Logger
     */
    public Memory(ArrayList<AbstractMeasure> data, Logger logger)  {
        this.data = data;
        this.logger = logger;
    }

    /**
     * Default constructor
     * 
     * Initializes measures with an empty list and uses default logger.
     */
    public Memory() {
        this.data = new ArrayList<>();
        this.logger = Logger.getLogger("default");
    }

    /**
     * Returns list of measures
     * 
     * @return List of measures
     */
    public Iterable<AbstractMeasure> asIterable() {
        return data;
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
     * Saves a measure
     * 
     * @param measure Measure
     */
    @Override
    public void persist(AbstractMeasure measure) {
        logger.info("Storing measure for room " +  measure.getRoomIdentifier() + ", from device " + measure.getDeviceIdentifier() + ".");
        data.add(measure);
    }
    
}
