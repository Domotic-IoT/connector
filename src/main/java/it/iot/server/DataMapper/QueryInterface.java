package it.iot.server.DataMapper;

import java.util.Optional;

public interface QueryInterface {
    public Optional<String> getRoomIdentifier();
    public Optional<String> getDeviceIdentifier();
    public Optional<String> getType();
    public Optional<Integer> getFromTimestamp();
    public Optional<Integer> getToTimestamp();
}
