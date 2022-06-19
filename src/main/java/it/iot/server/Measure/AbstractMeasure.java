package it.iot.server.Measure;

public abstract class AbstractMeasure {
    private String roomIdentifier;
    private String deviceIdentifier;
    private double value;
    private double absoluteError;
    private int timestamp;

    public AbstractMeasure() {
        this.roomIdentifier = "";
        this.deviceIdentifier = "";
        this.value = 0.0;
        this.absoluteError = 0.0;
        this.timestamp = 0;
    }

    public AbstractMeasure(
        String roomIdentifier,
        String deviceIdentifier,
        double value,
        double absoluteError,
        int timestamp
    ) {
        this.roomIdentifier = roomIdentifier;
        this.deviceIdentifier = deviceIdentifier;
        this.value = value;
        this.absoluteError = absoluteError;
        this.timestamp = timestamp;
    }

    public String getRoomIdentifier() {
        return roomIdentifier;
    }

    public String getDeviceIdentifier() {
        return deviceIdentifier;
    }

    public double getValue() {
        return value;
    }

    public double getAbsoluteError() {
        return absoluteError;
    }

    public int getTimestamp() {
        return timestamp;
    }

    abstract public void accept(VisitorInterface visitor);
}
