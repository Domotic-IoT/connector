package it.iot.server.DataMapper;

import java.util.Optional;

public class SimpleQuery implements QueryInterface {
    private Optional<String> roomIdentifier;
    private Optional<String> deviceIdentifier;
    private Optional<String> type;
    private Optional<Integer> fromTimestamp;
    private Optional<Integer> toTimestamp;

    public SimpleQuery() {
        roomIdentifier = Optional.empty();
        deviceIdentifier = Optional.empty();
        type = Optional.empty();
        fromTimestamp = Optional.empty();
        toTimestamp = Optional.empty();
    }

    @Override
    public Optional<String> getRoomIdentifier() {
        return roomIdentifier;
    }

    @Override
    public Optional<String> getDeviceIdentifier() {
        return deviceIdentifier;
    }

    @Override
    public Optional<String> getType() {
        return type;
    }

    @Override
    public Optional<Integer> getFromTimestamp() {
        return fromTimestamp;
    }

    @Override
    public Optional<Integer> getToTimestamp() {
        return toTimestamp;
    }
    
    public SimpleQuery roomIdentifier(String roomIdentifier) {
        this.roomIdentifier = Optional.of(roomIdentifier);
        return this;
    }
    
    public SimpleQuery deviceIdentifier(String deviceIdentifier) {
        this.deviceIdentifier = Optional.of(deviceIdentifier);
        return this;
    }
    
    public SimpleQuery type(String type) {
        this.type = Optional.of(type);
        return this;
    }
    
    public SimpleQuery fromTimestamp(int timestamp) {
        this.fromTimestamp = Optional.of(timestamp);
        return this;
    }
    
    public SimpleQuery toTimestamp(int timestamp) {
        this.toTimestamp = Optional.of(timestamp);
        return this;
    }
}
