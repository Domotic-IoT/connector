package it.iot.server.Recommender;

import org.apache.log4j.Logger;
import it.iot.server.Recommender.Classifier.ClassifierInterface;

public class SimpleReflex implements RecommenderInterface{
    private StateReader stateReader;
    private ClassifierInterface classifier;
    private ActionPerformerInterface actionPerformer;
    private Logger logger;

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

    public StateReader getStateReader() {
        return stateReader;
    }

    public ClassifierInterface getClassifier() {
        return classifier;
    }

    public ActionPerformerInterface getActionPerformer() {
        return actionPerformer;
    }

    public Logger getLogger() {
        return logger;
    }

    @Override
    public void activate() {
        logger.info("SimpleReflex recommender activated.");
        double[] state = stateReader.read();
        String action = classifier.classify(state);
        actionPerformer.write(action);
    }

    @Override
    public void stop() {
        logger.info("SimpleReflex recommender stopped.");
    }
}
