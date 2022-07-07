package it.iot.server.Recommender;

import java.util.Arrays;
import org.apache.log4j.Logger;
import it.iot.server.DataMapper.MeasureInterface;

public class StateReader {
    private MeasureInterface mapper;
    private Logger logger;

    public StateReader(MeasureInterface mapper, Logger logger) {
        this.mapper = mapper;
        this.logger = logger;
    }

    public StateReader(MeasureInterface mapper) {
        this.mapper = mapper;
        this.logger = Logger.getLogger("default");
    }

    public MeasureInterface getMapper() {
        return mapper;
    }

    public Logger getLogger() {
        return logger;
    }

    /**
     * @todo Implement me
     * @return
     */
    public double[] read() {
        // roomTemp roomHum extTemp extHum
        double[] state = {23.0, 50.0, 27.5, 58.0};
        logger.info("Reading state: " + Arrays.toString(state) + ".");
        return state;
    }
}
