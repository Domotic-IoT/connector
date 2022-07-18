package it.iot.server.Measure;

/**
 * A measure of fine dust
 * 
 * @author Marco Zanella
 */
public class FineDust extends AbstractMeasure {
    /**
     * Default constructor
     */
    public FineDust() {}
    
    /**
     * Constructor
     * 
     * @param roomIdentifier   Identifier of room
     * @param deviceIdentifier Identifier of device
     * @param value            Value
     * @param absoluteError    Absolute error
     * @param timestamp        Timestamp
     */
    public FineDust(
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
