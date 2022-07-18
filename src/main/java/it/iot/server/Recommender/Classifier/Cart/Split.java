package it.iot.server.Recommender.Classifier.Cart;

/**
 * A leaf node
 * 
 * Splits are hard and univariate. This class implements the Visitor
 * and the Composite design patterns.
 * 
 * @author Marco Zanella
 */
public class Split implements NodeInterface {
    /**
     * Feature to split on
     */
    private int feature;

    /**
     * Threshold to split on
     */
    private double threshold;

    /**
     * Left subtree
     */
    private NodeInterface left;

    /**
     * Right subtree
     */
    private NodeInterface right;

    /**
     * Constructor
     * 
     * @param feature   Feature to split on
     * @param threshold Threshold to split on
     * @param left      Left subtree
     * @param right     Right subtree
     */
    public Split(
        int feature,
        double threshold,
        NodeInterface left,
        NodeInterface right
    ) {
        this.feature = feature;
        this.threshold = threshold;
        this.left = left;
        this.right = right;
    }

    /**
     * Returns feature to split on
     * 
     * @return Feature to split on
     */
    public int getFeature() {
        return feature;
    }

    /**
     * Returns threshold to split on
     * 
     * @return Threshold to split on
     */
    public double getThreshold() {
        return threshold;
    }

    /**
     * Returns left subtree
     * 
     * @return Left subtree
     */
    public NodeInterface getLeft() {
        return left;
    }

    /**
     * Returns right subtree
     * 
     * @return Right subtree
     */
    public NodeInterface getRight() {
        return right;
    }

    /**
     * Classifies a point
     * 
     * @param point Point to classify
     * @return Classification output
     */
    @Override
    public String classify(double[] point) {
        return point[feature] < threshold
            ? left.classify(point)
            : right.classify(point)
        ;
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
