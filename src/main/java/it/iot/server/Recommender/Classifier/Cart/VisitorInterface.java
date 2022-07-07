package it.iot.server.Recommender.Classifier.Cart;

public interface VisitorInterface {
    public void visit(Leaf leaf);
    public void visit(Split split);
}
