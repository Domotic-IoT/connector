package it.iot.server.Measure;

public class Humidity extends AbstractMeasure {
    public Humidity() {}
    
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

    @Override
    public void accept(VisitorInterface visitor) {
        visitor.visit(this);
    }
}