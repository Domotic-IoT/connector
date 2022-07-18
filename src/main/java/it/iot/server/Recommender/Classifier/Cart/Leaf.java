package it.iot.server.Recommender.Classifier.Cart;

/**
 * A leaf node
 * 
 * This class implements the Visitor and the Composite design patterns.
 * 
 * @author Marco Zanella
 */
public class Leaf implements NodeInterface {
    /**
     * Classification output
     */
    private String label;

    /**
     * Constructor
     * 
     * @param label Classification output
     */
    public Leaf(String label) {
        this.label = label;
    }

    /**
     * Returns label
     * 
     * @return Label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Classifies a point
     * 
     * @param point Point to classify
     * @return Classification output
     */
    @Override
    public String classify(double[] point) {
        return label;
    }

    /**
     * Accepts a visitor
     * 
     * @param visitor Visitor
     */
    public void accept(VisitorInterface visitor) {
        visitor.visit(this);
    }
}
