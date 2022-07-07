package it.iot.server.Recommender.Classifier.Cart;

public class Leaf implements NodeInterface {
    private String label;

    public Leaf(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public String classify(double[] point) {
        return label;
    }

    public void accept(VisitorInterface visitor) {
        visitor.visit(this);
    }
}
