package it.iot.server;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import it.iot.server.Recommender.Classifier.Cart.Leaf;

/**
 * Unit test for a CART leaf
 */
public class NodeTest {
    /**
     * Tests getLabel method
     */
    @Test
    public void testGetLabel() {
        Leaf leaf = new Leaf("A");
        assertEquals("A", leaf.getLabel());
    }

    /**
     * Tests classify method
     */
    @Test
    public void testClassify() {
        double[] point = {0};
        Leaf leaf = new Leaf("A");
        assertEquals("A", leaf.classify(point));
    }
}
