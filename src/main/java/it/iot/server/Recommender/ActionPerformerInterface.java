package it.iot.server.Recommender;

/**
 * Performs an action on a device
 * 
 * @author Marco Zanella
 */
public interface ActionPerformerInterface {
    /**
     * Writes an action to the appropriate channel
     * 
     * @param action Action to perform
     */
    public void write(String action);
}
