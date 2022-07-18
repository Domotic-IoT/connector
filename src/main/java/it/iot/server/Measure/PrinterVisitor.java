package it.iot.server.Measure;

/**
 * Prints a measure to standard output
 * 
 * @author Marco Zanella
 */
public class PrinterVisitor implements VisitorInterface {
    /**
     * Visits a temperature
     * 
     * @param temperature Temperature
     */
    @Override
    public void visit(Temperature temperature) {
        System.out.println(temperature.getValue() + " degrees");
    }

    /**
     * Visits a humidity
     * 
     * @param humidity Humidity
     */
    @Override
    public void visit(Humidity humidity) {
        System.out.println(humidity.getValue() + "%");
    }

    /**
     * Visits a heat index
     * 
     * @param heatIndex Heat index
     */
    @Override
    public void visit(HeatIndex heatIndex) {
        System.out.println(heatIndex.getValue() + " degrees");
    }

    /**
     * Visits a light level
     * 
     * @param lightLevel Light level
     */
    @Override
    public void visit(LightLevel lightLevel) {
        System.out.println(lightLevel.getValue());
    }

    /**
     * Visits a fine dust
     * @param fineDust Fine dust
     */
    @Override
    public void visit(FineDust fineDust) {
        System.out.println(fineDust.getValue());
    }
}
