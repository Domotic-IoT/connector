package it.iot.server.Measure;

/**
 * A measure
 * 
 * This class implements the Visitor design patter (is visitable).
 * 
 * @author Marco Zanella
 */
public abstract class AbstractMeasure {
    /**
     * Identifier of room
     */
    private String roomIdentifier;

    /**
     * Identifier of device
     */
    private String deviceIdentifier;

    /**
     * Value
     */
    private double value;

    /**
     * Absolute error
     */
    private double absoluteError;

    /**
     * Timestamp
     */
    private int timestamp;

    /**
     * Default constructor
     * 
     * Initializes every value to its logical zero
     */
    public AbstractMeasure() {
        this.roomIdentifier = "";
        this.deviceIdentifier = "";
        this.value = 0.0;
        this.absoluteError = 0.0;
        this.timestamp = 0;
    }

    /**
     * Constructor
     * 
     * @param roomIdentifier   Identifier of room
     * @param deviceIdentifier Identifier of device
     * @param value            Value
     * @param absoluteError    Absolute error
     * @param timestamp        Timestamp
     */
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

    /**
     * Returns identifier of room
     * 
     * @return Identifier of room
     */
    public String getRoomIdentifier() {
        return roomIdentifier;
    }

    /**
     * Returns identifier of device
     * 
     * @return Identifier of device
     */
    public String getDeviceIdentifier() {
        return deviceIdentifier;
    }

    /**
     * Returns value
     * 
     * @return Value
     */
    public double getValue() {
        return value;
    }

    /**
     * Returns absolute error
     * 
     * @return Absolute error
     */
    public double getAbsoluteError() {
        return absoluteError;
    }

    /**
     * Returns timestamp
     * 
     * @return Timestamp
     */
    public int getTimestamp() {
        return timestamp;
    }

    /**
     * Accepts a visitor
     * 
     * @param visitor Visitor
     */
    abstract public void accept(VisitorInterface visitor);
}
