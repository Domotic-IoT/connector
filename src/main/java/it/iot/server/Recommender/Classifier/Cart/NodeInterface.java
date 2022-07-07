package it.iot.server.Recommender.Classifier.Cart;

import java.io.Serializable;
import it.iot.server.Recommender.Classifier.ClassifierInterface;

public interface NodeInterface extends ClassifierInterface, Serializable {
    public void accept(VisitorInterface visitor);
}
