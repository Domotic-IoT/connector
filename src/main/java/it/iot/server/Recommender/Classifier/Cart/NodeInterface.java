package it.iot.server.Recommender.Classifier.Cart;

import java.io.Serializable;
import it.iot.server.Recommender.Classifier.ClassifierInterface;

/**
 * A node
 * 
 * This class implements the Visitor and the Composite design patterns.
 * 
 * @author Marco Zanella
 */
public interface NodeInterface extends ClassifierInterface, Serializable {
    /**
     * Accepts a visitor
     * 
     * @param visitor Visitor
     */
    public void accept(VisitorInterface visitor);
}
