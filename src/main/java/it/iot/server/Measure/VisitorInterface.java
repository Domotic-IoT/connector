package it.iot.server.Measure;

public interface VisitorInterface {
    public void visit(Temperature temperature);
    public void visit(Humidity humidity);
    public void visit(HeatIndex heatIndex);
    public void visit(LightLevel lightLevel);
    public void visit(FineDust fineDust);
}
