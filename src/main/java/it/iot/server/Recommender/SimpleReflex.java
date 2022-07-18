package it.iot.server.Recommender;

import org.apache.log4j.Logger;
import it.iot.server.Recommender.Classifier.ClassifierInterface;

/**
 * Recommender based on a simple reflex agent.
 * 
 * Reads current state from a database, uses a classifier to decide the
 * best action, then sends the action through an action performer.
 * 
 * @author Marco Zanella
 */
public class SimpleReflex implements RecommenderInterface{
    /**
     * State reader
     */
    private StateReader stateReader;

    /**
     * Classifier
     */
    private ClassifierInterface classifier;

    /**
     * Action performer
     */
    private ActionPerformerInterface actionPerformer;

    /**
     * Logger
     */
    private Logger logger;

    /**
     * Constructor
     * 
     * @param stateReader     State reader
     * @param classifier      Classifier
     * @param actionPerformer Action performer
     * @param logger          Logger
     */
    public SimpleReflex(
        StateReader stateReader,
        ClassifierInterface classifier,
        ActionPerformerInterface actionPerformer,
        Logger logger
    ) {
        this.stateReader = stateReader;
        this.classifier = classifier;
        this.actionPerformer = actionPerformer;
        this.logger = logger;
    }

    /**
     * Constructor
     * 
     * Uses default logger
     * 
     * @param stateReader     State reader
     * @param classifier      Classifier
     * @param actionPerformer Action performer
     */
    public SimpleReflex(
        StateReader stateReader,
        ClassifierInterface classifier,
        ActionPerformerInterface actionPerformer
    ) {
        this.stateReader = stateReader;
        this.classifier = classifier;
        this.actionPerformer = actionPerformer;
        this.logger = Logger.getLogger("default");
    }

    /**
     * Returns state reader
     * 
     * @return State reader
     */
    public StateReader getStateReader() {
        return stateReader;
    }

    /**
     * Returns classifier
     * 
     * @return Classifier
     */
    public ClassifierInterface getClassifier() {
        return classifier;
    }

    /**
     * Returns action performer
     * 
     * @return Action performer
     */
    public ActionPerformerInterface getActionPerformer() {
        return actionPerformer;
    }

    /**
     * Returns logger
     * 
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
        logger.info("SimpleReflex recommender activated.");
        double[] state = stateReader.read();
        String action = classifier.classify(state);
        actionPerformer.write(action);
    }

    /**
     * Stops recommender
     */
    @Override
    public void stop() {
        logger.info("SimpleReflex recommender stopped.");
    }
}
