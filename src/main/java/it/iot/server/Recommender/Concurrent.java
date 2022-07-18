package it.iot.server.Recommender;

import org.apache.log4j.Logger;

/**
 * Recommender running on a separate thread
 * 
 * This class implements the Decorator design pattern.
 * 
 * @author Marco Zanella
 */
public class Concurrent implements RecommenderInterface, Runnable {
    /**
     * Decorated recommender
     */
    RecommenderInterface recommender;

    /**
     * Thread to run on
     */
    Thread thread;

    /**
     * Sleep time between subsequent activations
     */
    int sleepTime;

    /**
     * Logger
     */
    Logger logger;

    /**
     * Constructor
     * 
     * @param recommender Decorated recommender
     * @param sleepTime   Sleep time
     * @param logger      Logger
     */
    public Concurrent(
        RecommenderInterface recommender,
        int sleepTime,
        Logger logger
    ) {
        this.recommender = recommender;
        this.thread = new Thread(this);
        this.sleepTime = sleepTime;
        this.logger = logger;
    }

    /**
     * Constructor
     * 
     * Uses default logger.
     * 
     * @param recommender Decorated recommender
     * @param sleepTime   Sleep time
     */
    public Concurrent(
        RecommenderInterface recommender,
        int sleepTime
    ) {
        this.recommender = recommender;
        this.thread = new Thread(this);
        this.sleepTime = sleepTime;
        this.logger = Logger.getLogger("defaut");
    }

    /**
     * Returns decorated recommender
     * 
     * @return Decorated recommender
     */
    public RecommenderInterface getRecommender() {
        return recommender;
    }

    /**
     * Returns sleep time
     * 
     * @return Sleep time
     */
    public int getSleepTime() {
        return sleepTime;
    }

    /**
     * Returns logger
     * @return Logger
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * Activates recommender
     */
    @Override
    public void activate() {
        logger.info("Concurrent recommender activated.");
        thread.start();
    }

    /**
     * Starts recommender on a separate thread
     */
    @Override
    public void run() {
        logger.info("Concurrent recommender running.");
        while (true) {
            try {
                Thread.sleep(sleepTime);
                recommender.activate();
            }
            catch (Exception e) {
                // Do nothing
            }
        }
    }

    /**
     * Stops recommender
     */
    @Override
    public void stop() {
        thread.interrupt();
        logger.info("Concurrent recommender stopped.");
    }
}
