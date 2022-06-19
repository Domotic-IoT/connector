package it.iot.server.DataMapper;

import it.iot.server.Measure.AbstractMeasure;

public interface MeasureInterface {
    public void create(AbstractMeasure measure);
    public Iterable<AbstractMeasure> search(QueryInterface query);
}
