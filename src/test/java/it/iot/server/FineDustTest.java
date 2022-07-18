package it.iot.server;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import it.iot.server.Measure.FineDust;

/**
 * Unit test for fine dust measure
 * 
 * @author Marco Zanella
 */
public class FineDustTest {
    /**
     * Tests getRoomIdentifier method
     */
    @Test
    public void testRoomIdentifier() {
        assertEquals("roomIdentifier", getMeasure().getRoomIdentifier());
    }

    /**
     * Tests getDeviceIdentifier method
     */
    @Test
    public void testDeviceIdentifier() {
        assertEquals("deviceIdentifier", getMeasure().getDeviceIdentifier());
    }

    /**
     * Tests getValue method
     */
    @Test
    public void testValue() {
        assertEquals(1.0, getMeasure().getValue(), 0.0);
    }

    /**
     * Tests getAbsoluteError method
     */
    @Test
    public void testAbsoluteError() {
        assertEquals(2.0, getMeasure().getAbsoluteError(), 0.0);
    }

    /**
     * Tests getTimestamp method
     */
    @Test
    public void testTimestamp() {
        assertEquals(3, getMeasure().getTimestamp());
    }

    /**
     * Builds a measure for test
     * 
     * @return a measure
     */
    private FineDust getMeasure() {
        return new FineDust(
            "roomIdentifier",
            "deviceIdentifier",
            1.0,
            2.0,
            3
        );
    }
}
