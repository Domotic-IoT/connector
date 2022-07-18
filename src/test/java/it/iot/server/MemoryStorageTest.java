package it.iot.server;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import it.iot.server.Measure.AbstractMeasure;
import it.iot.server.Measure.Temperature;
import it.iot.server.Storage.Memory;

/**
 * Unit test for in-memory storage
 */
public class MemoryStorageTest {
    /**
     * Tests persit method
     */
    @Test
    public void testPersist() {
        Memory storage = new Memory();
        AbstractMeasure measure = new Temperature();
        storage.persist(measure);
        assertEquals(measure, storage.asIterable().iterator().next());
    }
}
