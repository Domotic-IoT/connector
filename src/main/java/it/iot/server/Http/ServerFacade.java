package it.iot.server.Http;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

import it.iot.server.DataMapper.MeasureInterface;

/**
 * Simple webserver exposing REST API for measures
 * 
 * This class implements the Facade design pattern.
 * 
 * @author Marco Zanella
 */
public class ServerFacade implements Runnable {
    /**
     * Serve object
     */
    private Server server;

    /**
     * Thread on which server runs
     */
    private Thread thread;

    /**
     * Logger
     */
    private Logger logger;

    /**
     * Constructor
     * 
     * @param port   Port to listen to
     * @param mapper Data mapper for measures
     */
    public ServerFacade(int port, MeasureInterface mapper) {
        server = new Server(port);
        thread = new Thread(this);
        thread.setDaemon(true);
        logger = Logger.getLogger("default");
        ServletContextHandler handler = new ServletContextHandler();
        handler.setContextPath("/");
        handler.setAttribute("mapper", mapper);
        server.setHandler(handler);

        // Adds handlers
        handler.addServlet(MeasureServlet.class, "/measures");
    }

    /**
     * Starts server
     */
    public void start() {
        logger.info("Webserver started.");
        thread.start();
    }

    /**
     * Stops server
     */
    public void stop() {
        try {
            server.stop();
            server.join();
            thread.interrupt();
        }
        catch (InterruptedException e) {
            // Do nothing
        }
        catch (Exception e) {
            logger.error("Cannot stop webserver.");
            logger.debug(ExceptionUtils.getStackTrace(e));
        }
        logger.info("Webserver stopped.");
    }

    /**
     * Activates server on a separate thread
     */
    @Override
    public void run() {
        try {
            server.start();
        }
        catch (Exception e) {
            logger.error("Cannot start webserver.");
            logger.debug(ExceptionUtils.getStackTrace(e));
        }
    }
}
