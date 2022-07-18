package it.iot.server.Storage;

import it.iot.server.Measure.AbstractMeasure;

/**
 * A storage medium
 * 
 * @author Marco Zanella
 */
public interface StorageInterface {
    /**
     * Saves a measure
     * 
     * @param measure Measure
     */
    public void persist(AbstractMeasure measure);
}
