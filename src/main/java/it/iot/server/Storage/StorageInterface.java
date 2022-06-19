package it.iot.server.Storage;

import it.iot.server.Measure.AbstractMeasure;

public interface StorageInterface {
    public void persist(AbstractMeasure measure);
}
