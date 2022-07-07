package it.iot.server.Recommender.Classifier.Cart;

public class Split implements NodeInterface {
    private int feature;
    private double threshold;
    private NodeInterface left;
    private NodeInterface right;

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

    public int getFeature() {
        return feature;
    }

    public double getThreshold() {
        return threshold;
    }

    public NodeInterface getLeft() {
        return left;
    }

    public NodeInterface getRight() {
        return right;
    }

    public String classify(double[] point) {
        return point[feature] < threshold
            ? left.classify(point)
            : right.classify(point)
        ;
    }

    public void accept(VisitorInterface visitor) {
        visitor.visit(this);
    }
}
