package it.iot.server.Recommender;

import org.apache.log4j.Logger;

public class Concurrent implements RecommenderInterface, Runnable {
    RecommenderInterface recommender;
    Thread thread;
    int sleepTime;
    Logger logger;

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

    public Concurrent(
        RecommenderInterface recommender,
        int sleepTime
    ) {
        this.recommender = recommender;
        this.thread = new Thread(this);
        this.sleepTime = sleepTime;
        this.logger = Logger.getLogger("defaut");
    }

    public RecommenderInterface getRecommender() {
        return recommender;
    }

    public int getSleepTime() {
        return sleepTime;
    }

    public Logger getLogger() {
        return logger;
    }

    @Override
    public void activate() {
        logger.info("Concurrent recommender activated.");
        thread.start();
    }

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

    @Override
    public void stop() {
        thread.interrupt();
        logger.info("Concurrent recommender stopped.");
    }
}
