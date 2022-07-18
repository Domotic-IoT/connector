package it.iot.server.Converter.Bson;

import org.bson.Document;

import it.iot.server.Measure.FineDust;
import it.iot.server.Measure.HeatIndex;
import it.iot.server.Measure.Humidity;
import it.iot.server.Measure.LightLevel;
import it.iot.server.Measure.Temperature;
import it.iot.server.Measure.VisitorInterface;

/**
 * Converts a measure into a BSON object.
 * 
 * @author Marco Zanella
 */
public class MeasureVisitor implements VisitorInterface {
    /**
     * BSON object
     */
    private Document document;

    /**
     * Returns BSON object
     * 
     * @return BSON object
     */
    public Document getDocument() {
        return document;
    }

    /**
     * Converts a temperature
     * 
     * @param temperature Temperature
     */
    @Override
    public void visit(Temperature temperature) {
        document = new Document();
        document
            .append("type", "temperature")
            .append("roomId", temperature.getRoomIdentifier())
            .append("deviceId", temperature.getDeviceIdentifier())
            .append("value", temperature.getValue())
            .append("absoluteError", temperature.getAbsoluteError())
            .append("timestamp", temperature.getTimestamp())
        ;
    }

    /**
     * Converts a humidity
     * 
     * @param humidity Humidity
     */
    @Override
    public void visit(Humidity humidity) {
        document = new Document();
        document
            .append("type", "humidity")
            .append("roomId", humidity.getRoomIdentifier())
            .append("deviceId", humidity.getDeviceIdentifier())
            .append("value", humidity.getValue())
            .append("absoluteError", humidity.getAbsoluteError())
            .append("timestamp", humidity.getTimestamp())
        ;
    }

    /**
     * Converts a heat index
     * 
     * @param heatIndex Heat index
     */
    @Override
    public void visit(HeatIndex heatIndex) {
        document = new Document();
        document
            .append("type", "heatIndex")
            .append("roomId", heatIndex.getRoomIdentifier())
            .append("deviceId", heatIndex.getDeviceIdentifier())
            .append("value", heatIndex.getValue())
            .append("absoluteError", heatIndex.getAbsoluteError())
            .append("timestamp", heatIndex.getTimestamp())
        ;
    }

    /**
     * Converts a light level
     * 
     * @param lightLevel Light level
     */
    @Override
    public void visit(LightLevel lightLevel) {
        document = new Document();
        document
            .append("type", "lightLevel")
            .append("roomId", lightLevel.getRoomIdentifier())
            .append("deviceId", lightLevel.getDeviceIdentifier())
            .append("value", lightLevel.getValue())
            .append("absoluteError", lightLevel.getAbsoluteError())
            .append("timestamp", lightLevel.getTimestamp())
        ;
    }
    
    /**
     * Converts a fine dust
     * 
     * @param fineDust Fine dust
     */
    @Override
    public void visit(FineDust fineDust) {
        document = new Document();
        document
            .append("type", "fineDust")
            .append("roomId", fineDust.getRoomIdentifier())
            .append("deviceId", fineDust.getDeviceIdentifier())
            .append("value", fineDust.getValue())
            .append("absoluteError", fineDust.getAbsoluteError())
            .append("timestamp", fineDust.getTimestamp())
        ;
    }
}
