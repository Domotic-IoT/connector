package it.iot.server.Measure;

public class FineDust extends AbstractMeasure {
    public FineDust() {}
    
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

    @Override
    public void accept(VisitorInterface visitor) {
        visitor.visit(this);
    }
}
