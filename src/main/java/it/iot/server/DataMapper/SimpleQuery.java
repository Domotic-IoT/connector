package it.iot.server.DataMapper;

import java.util.Optional;

/**
 * A simple query which allows setting basic parameters
 * 
 * @author Marco Zanella
 */
public class SimpleQuery implements QueryInterface {
    /**
     * Identifier of room
     */
    private Optional<String> roomIdentifier;

    /**
     * Identifier of device
     */
    private Optional<String> deviceIdentifier;

    /**
     * Type of measure
     */
    private Optional<String> type;

    /**
     * Minimum timestamp
     */
    private Optional<Integer> fromTimestamp;

    /**
     * Maximum timestamp
     */
    private Optional<Integer> toTimestamp;

    /**
     * Default constructor
     * 
     * Initializes every information to empty
     */
    public SimpleQuery() {
        roomIdentifier = Optional.empty();
        deviceIdentifier = Optional.empty();
        type = Optional.empty();
        fromTimestamp = Optional.empty();
        toTimestamp = Optional.empty();
    }

    /**
     * Returns identifier of room (if any)
     * 
     * @return Identifier of room
     */
    @Override
    public Optional<String> getRoomIdentifier() {
        return roomIdentifier;
    }

    /**
     * Returns identifier of device (if any)
     * 
     * @return Identifier of device
     */
    @Override
    public Optional<String> getDeviceIdentifier() {
        return deviceIdentifier;
    }

    /**
     * Returns measure type (if any)
     * 
     * @return Measure type
     */
    @Override
    public Optional<String> getType() {
        return type;
    }

    /**
     * Returns minimum timestamp considered by search (if any)
     * 
     * @return From timestamp
     */
    @Override
    public Optional<Integer> getFromTimestamp() {
        return fromTimestamp;
    }

    /**
     * Returns maximum timestamp considered by search (if any)
     * 
     * @return To timestamp
     */
    @Override
    public Optional<Integer> getToTimestamp() {
        return toTimestamp;
    }
    
    /**
     * Sets identifier of room
     * 
     * @param roomIdentifier Identifier of room
     * @return This query
     */
    public SimpleQuery roomIdentifier(String roomIdentifier) {
        this.roomIdentifier = Optional.of(roomIdentifier);
        return this;
    }
    
    /**
     * Sets identifier of device
     * 
     * @param deviceIdentifier Identifier of device
     * @return This query
     */
    public SimpleQuery deviceIdentifier(String deviceIdentifier) {
        this.deviceIdentifier = Optional.of(deviceIdentifier);
        return this;
    }
    
    /**
     * Sets measure type
     * @param type Type of measure
     * @return This query
     */
    public SimpleQuery type(String type) {
        this.type = Optional.of(type);
        return this;
    }
    
    /**
     * Sets minimum timestamp
     * 
     * @param timestamp Minimum timestamp
     * @return This query
     */
    public SimpleQuery fromTimestamp(int timestamp) {
        this.fromTimestamp = Optional.of(timestamp);
        return this;
    }
    
    /**
     * Sets maximum timestamp
     * 
     * @param timestamp Maximum timestamp
     * @return This query
     */
    public SimpleQuery toTimestamp(int timestamp) {
        this.toTimestamp = Optional.of(timestamp);
        return this;
    }
}
