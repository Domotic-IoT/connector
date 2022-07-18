package it.iot.server.Ingestor;

/**
 * Intermediate datatype for a measure received through MQTT
 * 
 * @author Marco Zanella
 */
public class MqttMeasure {
    /**
     * Identifier of device
     */
    private String deviceIdentifier;

    /**
     * Value
     */
    private double value;

    /**
     * Absolute error
     */
    private double absoluteError;

    /**
     * Constructor
     * 
     * @param deviceIdentifier Identifier of device
     * @param value            Value
     * @param absoluteError    Absolute error
     */
    public MqttMeasure(String deviceIdentifier, double value, double absoluteError) {
        this.deviceIdentifier = deviceIdentifier;
        this.value = value;
        this.absoluteError = absoluteError;
    }

    /**
     * Default constructor
     * 
     * Initializes every value to its logical zero.
     */
    public MqttMeasure() {
        this.deviceIdentifier = "";
        this.value = 0.0;
        this.absoluteError = 0.0;
    }

    /**
     * Returns identifier of device
     * 
     * @return Identifier of device
     */
    public String getDeviceIdentifier() {
        return deviceIdentifier;
    }

    /**
     * Returns value
     * 
     * @return Value
     */
    public double getValue() {
        return value;
    }

    /**
     * Returns absolute error
     * 
     * @return Absolute error
     */
    public double getAbsoluteError() {
        return absoluteError;
    }
}
