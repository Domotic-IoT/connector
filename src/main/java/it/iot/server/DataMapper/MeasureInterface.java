package it.iot.server.DataMapper;

import it.iot.server.Measure.AbstractMeasure;

/**
 * Data mapper for a measure
 * 
 * @author Marco Zanella
 */
public interface MeasureInterface {
    /**
     * Inserts a measure into database
     * 
     * @param measure Measure
     */
    public void create(AbstractMeasure measure);

    /**
     * Searches for measures
     * 
     * @param query Query object
     * @return List of measures
     */
    public Iterable<AbstractMeasure> search(QueryInterface query);
}
