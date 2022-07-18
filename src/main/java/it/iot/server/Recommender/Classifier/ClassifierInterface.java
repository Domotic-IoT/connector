package it.iot.server.Recommender.Classifier;

/**
 * Interface of a classifier
 * 
 * Classifiers accept a point as input and return a classification output.
 * 
 * @author Marco Zanella
 */
public interface ClassifierInterface {
    /**
     * Classifies a point
     * 
     * @param point Point to classify
     * @return Classification output
     */
    public String classify(double[] point);
}
