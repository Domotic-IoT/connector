package it.iot.server.Converter.Bson;

import org.bson.Document;

import it.iot.server.Measure.FineDust;
import it.iot.server.Measure.HeatIndex;
import it.iot.server.Measure.Humidity;
import it.iot.server.Measure.LightLevel;
import it.iot.server.Measure.Temperature;
import it.iot.server.Measure.VisitorInterface;

public class MeasureVisitor implements VisitorInterface {
    private Document document;

    public Document getDocument() {
        return document;
    }

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
