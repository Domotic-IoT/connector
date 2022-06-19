package it.iot.server.Measure;

public class PrinterVisitor implements VisitorInterface {
    @Override
    public void visit(Temperature temperature) {
        System.out.println(temperature.getValue() + " degrees");
    }

    @Override
    public void visit(Humidity humidity) {
        System.out.println(humidity.getValue() + "%");
    }
    @Override
    public void visit(HeatIndex heatIndex) {
        System.out.println(heatIndex.getValue() + " degrees");
    }
    @Override
    public void visit(LightLevel lightLevel) {
        System.out.println(lightLevel.getValue());
    }
    @Override
    public void visit(FineDust fineDust) {
        System.out.println(fineDust.getValue());
    }
}
