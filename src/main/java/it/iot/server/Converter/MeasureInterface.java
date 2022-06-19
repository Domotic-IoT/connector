package it.iot.server.Converter;

import it.iot.server.Measure.AbstractMeasure;

public interface MeasureInterface<T> {
    public T fromObject(AbstractMeasure measure);
    public AbstractMeasure toObject(T data);
}
