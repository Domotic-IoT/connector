package it.iot.server;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import it.iot.server.Measure.AbstractMeasure;
import it.iot.server.Measure.FineDust;
import it.iot.server.Measure.HeatIndex;
import it.iot.server.Measure.Humidity;
import it.iot.server.Measure.LightLevel;
import it.iot.server.Measure.Temperature;
import it.iot.server.Measure.VisitorInterface;

/**
 * Unit test for measure visitor
 * 
 * @author Marco Zanella
 */
public class MeasureVisitorTest {
    /**
     * Visitor which tells type of a measure
     * 
     * @author Marco Zanella
     */
    class TypeVisitor implements VisitorInterface {
        /**
         * Type of a measure
         */
        private String type;

        /**
         * Returns type of a measure
         * @return
         */
        public String getType() {
            return type;
        }

        /**
         * Visits a fine dust
         */
        public void visit(FineDust fineDust) {
            type = "fineDust";
        }

        /**
         * Visits a heat index
         */
        public void visit(HeatIndex heatIndex) {
            type = "heatIndex";
        }

        /**
         * Visits a humidity
         */
        public void visit(Humidity humidity) {
            type = "humidity";
        }

        /**
         * Visits a light level
         */
        public void visit(LightLevel lightLevel) {
            type = "lightLevel";
        }

        /**
         * Visits a temperature
         */
        public void visit(Temperature temperature) {
            type = "temperature";
        }
    }

    /**
     * Test visit fine dust method
     */
    @Test
    public void testVisitFineDust() {
        AbstractMeasure measure = new FineDust();
        TypeVisitor visitor = new TypeVisitor();
        measure.accept(visitor);
        assertEquals("fineDust", visitor.getType());
    }

    /**
     * Test visit heat index method
     */
    @Test
    public void testVisitHeatIndex() {
        AbstractMeasure measure = new HeatIndex();
        TypeVisitor visitor = new TypeVisitor();
        measure.accept(visitor);
        assertEquals("heatIndex", visitor.getType());
    }

    /**
     * Test visit humidity method
     */
    @Test
    public void testVisitHumidity() {
        AbstractMeasure measure = new Humidity();
        TypeVisitor visitor = new TypeVisitor();
        measure.accept(visitor);
        assertEquals("humidity", visitor.getType());
    }

    /**
     * Test visit light level method
     */
    @Test
    public void testVisitLightLevel() {
        AbstractMeasure measure = new LightLevel();
        TypeVisitor visitor = new TypeVisitor();
        measure.accept(visitor);
        assertEquals("lightLevel", visitor.getType());
    }

    /**
     * Test visit temperature method
     */
    @Test
    public void testVisitTemperature() {
        AbstractMeasure measure = new Temperature();
        TypeVisitor visitor = new TypeVisitor();
        measure.accept(visitor);
        assertEquals("temperature", visitor.getType());
    }
}
