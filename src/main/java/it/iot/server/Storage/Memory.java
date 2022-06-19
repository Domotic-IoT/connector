package it.iot.server.Storage;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import it.iot.server.Measure.AbstractMeasure;

public class Memory implements StorageInterface {
    private ArrayList<AbstractMeasure> data;
    private Logger logger;

    public Memory(ArrayList<AbstractMeasure> data, Logger logger)  {
        this.data = data;
    }

    public Memory() {
        this.data = new ArrayList<>();
        this.logger = Logger.getLogger("default");
    }

    public Iterable<AbstractMeasure> asIterable() {
        return data;
    }

    public Logger getLogger() {
        return logger;
    }

    @Override
    public void persist(AbstractMeasure measure) {
        logger.info("Storing measure for room " +  measure.getRoomIdentifier() + ", from device " + measure.getDeviceIdentifier() + ".");
        data.add(measure);
    }
    
}
