package it.iot.server.Ingestor;

import org.apache.log4j.Logger;

/**
 * Ingestor running on a separate thread
 * 
 * This class implements the Decorator design pattern.
 * 
 * @author Marco Zanella
 */
public class Concurrent implements IngestorInterface, Runnable {
    /**
     * Decorated ingestor
     */
    private IngestorInterface ingestor;

    /**
     * Thread to run on
     */
    private Thread thread;

    /**
     * Logger
     */
    private Logger logger;

    /**
     * Constructor
     * 
     * @param ingestor Decorated ingestor
     * @param logger   Logger
     */
    public Concurrent(IngestorInterface ingestor, Logger logger) {
        this.ingestor = ingestor;
        this.thread = new Thread(this);
        this.logger = logger;
    }

    /**
     * Constructor
     * 
     * Uses default logger.
     * 
     * @param ingestor Decorated ingestor
     */
    public Concurrent(IngestorInterface ingestor) {
        this.ingestor = ingestor;
        this.thread = new Thread(this);
        this.logger = Logger.getLogger("default");
    }

    /**
     * Activates ingestor
     */
    @Override
    public void activate() {
        logger.info("Concurrent ingestor activated.");
        thread.start();
    }

    /**
     * Runs ingestor on a separate thread
     */
    @Override
    public void run() {
        logger.info("Concurrent ingestor running.");
        ingestor.activate();
    }

    /**
     * Stops ingestor
     */
    @Override
    public void stop() {
        ingestor.stop();
        thread.interrupt();
        logger.info("Concurrent ingestor stopped.");
    }
}
