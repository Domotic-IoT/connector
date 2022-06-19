package it.iot.server.Ingestor;

import org.apache.log4j.Logger;

public class Concurrent implements IngestorInterface, Runnable {
    private IngestorInterface ingestor;
    private Thread thread;
    private Logger logger;

    public Concurrent(IngestorInterface ingestor, Logger logger) {
        this.ingestor = ingestor;
        this.thread = new Thread(this);
        this.logger = logger;
    }

    public Concurrent(IngestorInterface ingestor) {
        this.ingestor = ingestor;
        this.thread = new Thread(this);
        this.logger = Logger.getLogger("default");
    }

    @Override
    public void activate() {
        logger.info("Concurrent ingestor activated.");
        thread.start();
    }

    @Override
    public void run() {
        logger.info("Concurrent ingestor running.");
        ingestor.activate();
    }

    @Override
    public void stop() {
        ingestor.stop();
        thread.interrupt();
        logger.info("Concurrent ingestor stopped.");
    }
}
