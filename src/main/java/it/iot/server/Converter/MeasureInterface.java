package it.iot.server.Converter;

import it.iot.server.Measure.AbstractMeasure;

/**
 * Converts a measure from and to a given type T
 * 
 * @author Marco Zanella
 */
public interface MeasureInterface<T> {
    /**
     * Converts from a measure
     * 
     * @param measure Measure
     * @return Converted data
     */
    public T fromObject(AbstractMeasure measure);

    /**
     * Converts into a measure
     * 
     * @param data Data
     * @return Measure
     */
    public AbstractMeasure toObject(T data);
}
