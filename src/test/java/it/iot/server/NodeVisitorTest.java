package it.iot.server;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import it.iot.server.Recommender.Classifier.Cart.Leaf;
import it.iot.server.Recommender.Classifier.Cart.Split;
import it.iot.server.Recommender.Classifier.Cart.VisitorInterface;

/**
 * Unit test for node visitor
 * 
 * @author Marco Zanella
 */
public class NodeVisitorTest {
    /**
     * Visitor which tells type of a node
     * 
     * @author Marco Zanella
     */
    class TypeVisitor implements VisitorInterface {
        /**
         * Type of a node
         */
        private String type;

        /**
         * Returns type of a node
         * @return
         */
        public String getType() {
            return type;
        }

        /**
         * Visits a leaf
         */
        public void visit(Leaf leaf) {
            type = "leaf";
        }

        /**
         * Visits a split
         */
        public void visit(Split split) {
            type = "split";
        }
    }

    /**
     * Test visit leaf method
     */
    @Test
    public void testVisitFineDust() {
        Leaf leaf = new Leaf("A");
        TypeVisitor visitor = new TypeVisitor();
        leaf.accept(visitor);
        assertEquals("leaf", visitor.getType());
    }

    /**
     * Test visit split method
     */
    @Test
    public void testVisitSplit() {
        Leaf left = new Leaf("L");
        Leaf right = new Leaf("R");
        Split split = new Split(0, 0.5, left, right);
        TypeVisitor visitor = new TypeVisitor();
        split.accept(visitor);
        assertEquals("split", visitor.getType());
    }
}
