package it.iot.server.Ingestor;

/**
 * An ingestor
 * 
 * Ingestors are responsible of receiving raw measures and inserting them
 * into a suitable storage medium.
 * 
 * @author Marco Zanella
 */
public interface IngestorInterface {
    /**
     * Activates ingestor
     */
    public void activate();

    /**
     * Stops ingestor
     */
    public void stop();
}
