package it.iot.server.Storage;

import org.apache.log4j.Logger;

import it.iot.server.DataMapper.MongoDb.Measure;
import it.iot.server.Measure.AbstractMeasure;

public class Database implements StorageInterface {
    private Measure mapper;
    private Logger logger;

    public Database(Measure mapper, Logger logger) {
        this.mapper = mapper;
        this.logger = logger;
    }

    public Database(Measure mapper) {
        this.mapper = mapper;
        this.logger = Logger.getLogger("default");
    }

    public Measure getMapper() {
        return mapper;
    }

    public Logger getLogger() {
        return logger;
    }

    @Override
    public void persist(AbstractMeasure measure) {
        logger.info("Storing measure for room " +  measure.getRoomIdentifier() + ", from device " + measure.getDeviceIdentifier() + ".");
        mapper.create(measure);
    }
    
}
