package it.iot.server.Measure;

public class HeatIndex extends AbstractMeasure {
    public HeatIndex() {}
    
    public HeatIndex(
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