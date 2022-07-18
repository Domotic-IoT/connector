package it.iot.server.Recommender;

import java.util.Arrays;
import org.apache.log4j.Logger;
import it.iot.server.DataMapper.MeasureInterface;
import it.iot.server.DataMapper.SimpleQuery;
import it.iot.server.Measure.AbstractMeasure;

public class StateReader {
    public static final int timespan = 30 * 60;
    private String roomIdentifier;
    private MeasureInterface mapper;
    private Logger logger;

    public StateReader(String roomIdentifier, MeasureInterface mapper, Logger logger) {
        this.roomIdentifier = roomIdentifier;
        this.mapper = mapper;
        this.logger = logger;
    }

    public StateReader(String roomIdentifier, MeasureInterface mapper) {
        this.roomIdentifier = roomIdentifier;
        this.mapper = mapper;
        this.logger = Logger.getLogger("default");
    }

    public String getRoomIdentifier() {
        return roomIdentifier;
    }

    public MeasureInterface getMapper() {
        return mapper;
    }

    public Logger getLogger() {
        return logger;
    }

    public double[] read() {
        double[] state = {
            readMeasure("temperature"),
            readMeasure("humidity"),
            readMeasure("lightLevel"),
            readMeasure("ozone"),
        };
        logger.info("Reading state: " + Arrays.toString(state) + ".");
        return state;
    }

    private double readMeasure(String type) {
        double value = 0.0;
        int size = 0;
        int now = (int) java.time.Instant.now().getEpochSecond();
        SimpleQuery query = new SimpleQuery();
        query
            .roomIdentifier(roomIdentifier)
            .fromTimestamp(now - timespan)
            .toTimestamp(now)
            .type(type)
        ;
        for (AbstractMeasure measure : mapper.search(query)) {
            value += measure.getValue();
            size++;
        }
        if (size > 0) {
            value /= size;
        }
        logger.info("Reading " + type + ": " + value + " (average of " + size + " measures).");
        return value;
    }
}
