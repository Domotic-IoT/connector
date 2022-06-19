package it.iot.server.Http;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

import it.iot.server.DataMapper.MeasureInterface;

public class ServerFacade implements Runnable {
    private Server server;
    private Thread thread;
    private Logger logger;

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

    public void start() {
        logger.info("Webserver started.");
        thread.start();
    }

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
