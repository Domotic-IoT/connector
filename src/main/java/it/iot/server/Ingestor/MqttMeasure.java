package it.iot.server.Ingestor;

public class MqttMeasure {
    private String deviceIdentifier;
    private double value;
    private double absoluteError;

    public MqttMeasure(String deviceIdentifier, double value, double absoluteError) {
        this.deviceIdentifier = deviceIdentifier;
        this.value = value;
        this.absoluteError = absoluteError;
    }

    public MqttMeasure() {
        this.deviceIdentifier = "";
        this.value = 0.0;
        this.absoluteError = 0.0;
    }

    public String getDeviceIdentifier() {
        return deviceIdentifier;
    }

    public double getValue() {
        return value;
    }

    public double getAbsoluteError() {
        return absoluteError;
    }
}
