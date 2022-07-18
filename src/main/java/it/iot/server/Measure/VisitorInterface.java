package it.iot.server.Measure;

/**
 * A visitor for a measure
 * 
 * This class implements the Visitor design pattern.
 * 
 * @author Marco Zanella
 */
public interface VisitorInterface {
    /**
     * Visits a temperature
     * 
     * @param temperature Temperature
     */
    public void visit(Temperature temperature);

    /**
     * Visits a humidity
     * 
     * @param humidity Humidity
     */
    public void visit(Humidity humidity);

    /**
     * Visits a heat index
     * 
     * @param heatIndex Heat index
     */
    public void visit(HeatIndex heatIndex);

    /**
     * Visits a light level
     * 
     * @param lightLevel Light level
     */
    public void visit(LightLevel lightLevel);

    /**
     * Visits a fine dust
     * @param fineDust Fine dust
     */
    public void visit(FineDust fineDust);
}
