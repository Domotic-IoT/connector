package it.iot.server.Storage;

import org.apache.log4j.Logger;

import it.iot.server.DataMapper.MongoDb.Measure;
import it.iot.server.Measure.AbstractMeasure;

/**
 * Storage medium based on a database
 * 
 * @author Marco Zanella
 */
public class Database implements StorageInterface {
    /**
     * Data mapper to interact with database
     */
    private Measure mapper;

    /**
     * Logger
     */
    private Logger logger;

    /**
     * Constructor
     * 
     * @param mapper Data mapper
     * @param logger Logger
     */
    public Database(Measure mapper, Logger logger) {
        this.mapper = mapper;
        this.logger = logger;
    }

    /**
     * Constructor
     * 
     * Uses default logger.
     * 
     * @param mapper Data mapper
     */
    public Database(Measure mapper) {
        this.mapper = mapper;
        this.logger = Logger.getLogger("default");
    }

    /**
     * Returns data mapper
     * 
     * @return Data mapper
     */
    public Measure getMapper() {
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
     * Saves a measure
     * 
     * @param measure Measure
     */
    @Override
    public void persist(AbstractMeasure measure) {
        logger.info("Storing measure for room " +  measure.getRoomIdentifier() + ", from device " + measure.getDeviceIdentifier() + ".");
        mapper.create(measure);
    }
    
}
