package it.iot.server.Measure;

/**
 * A measure of humidity
 * 
 * @author Marco Zanella
 */
public class Humidity extends AbstractMeasure {
    /**
     * Default constructor
     * 
     * Initializes every value to its logical zero
     */
    public Humidity() {}
    
    /**
     * Constructor
     * 
     * @param roomIdentifier   Identifier of room
     * @param deviceIdentifier Identifier of device
     * @param value            Value
     * @param absoluteError    Absolute error
     * @param timestamp        Timestamp
     */
    public Humidity(
        String roomIdentifier,
        String deviceIdentifier,
        double value,
        double absoluteError,
        int timestamp
    ) {
        super(
            roomIdentifier,
            deviceIdentifier,
            value,
            absoluteError,
            timestamp
        );
    }

    /**
     * Accepts a visitor
     * 
     * @param visitor Visitor
     */
    @Override
    public void accept(VisitorInterface visitor) {
        visitor.visit(this);
    }
}