package it.iot.server.DataMapper;

import java.util.Optional;

/**
 * Interface of a query to search for measures
 * 
 * @author Marco Zanella
 */
public interface QueryInterface {
    /**
     * Returns identifier of room (if any)
     * 
     * @return Identifier of room
     */
    public Optional<String> getRoomIdentifier();

    /**
     * Returns identifier of device (if any)
     * 
     * @return Identifier of device
     */
    public Optional<String> getDeviceIdentifier();

    /**
     * Returns measure type (if any)
     * 
     * @return Measure type
     */
    public Optional<String> getType();

    /**
     * Returns minimum timestamp considered by search (if any)
     * 
     * @return From timestamp
     */
    public Optional<Integer> getFromTimestamp();

    /**
     * Returns maximum timestamp considered by search (if any)
     * 
     * @return To timestamp
     */
    public Optional<Integer> getToTimestamp();
}
