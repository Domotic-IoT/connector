package it.iot.server;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import it.iot.server.Recommender.Classifier.Cart.Leaf;
import it.iot.server.Recommender.Classifier.Cart.Split;

/**
 * Unit test for a CART split
 */
public class SplitTest {
    /**
     * Tests getFeature method
     */
    @Test
    public void testGetFeature() {
        Leaf left = new Leaf("L");
        Leaf right = new Leaf("R");
        Split split = new Split(0, 0.5, left, right);
        assertEquals(0, split.getFeature());
    }

    /**
     * Tests getThreshold method
     */
    @Test
    public void testGetThreshold() {
        Leaf left = new Leaf("L");
        Leaf right = new Leaf("R");
        Split split = new Split(0, 0.5, left, right);
        assertEquals(0.5, split.getThreshold(), 0.0);
    }

    /**
     * Tests getLeft method
     */
    @Test
    public void testGetLeft() {
        Leaf left = new Leaf("L");
        Leaf right = new Leaf("R");
        Split split = new Split(0, 0.5, left, right);
        assertEquals(left, split.getLeft());
    }

    /**
     * Tests getRight method
     */
    @Test
    public void testGetRight() {
        Leaf left = new Leaf("L");
        Leaf right = new Leaf("R");
        Split split = new Split(0, 0.5, left, right);
        assertEquals(right, split.getRight());
    }

    /**
     * Tests classify method
     */
    @Test
    public void testClassify() {
        double[] point = {0};
        Leaf left = new Leaf("L");
        Leaf right = new Leaf("R");
        Split split = new Split(0, 0.5, left, right);
        assertEquals("R", split.classify(point));
    }
}
