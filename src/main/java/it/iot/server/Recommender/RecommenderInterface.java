package it.iot.server.Recommender;

/**
 * A recommender.
 * 
 * @author Marco Zanella
 */
public interface RecommenderInterface {
    /**
     * Activates recommender
     */
    public void activate();

    /**
     * Stops recommender
     */
    public void stop();
}
