package it.iot.server.Recommender.Classifier.Cart;

/**
 * Visitor for a node
 * 
 * This class implements the Visitor design pattern.
 * 
 * @author Marco Zanella
 */
public interface VisitorInterface {
    /**
     * Visits a leaf
     * 
     * @param leaf Leaf to visit
     */
    public void visit(Leaf leaf);

    /**
     * Visits a split
     * 
     * @param split Split to visit
     */
    public void visit(Split split);
}
