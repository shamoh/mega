package cz.kramolis.mega.runtime.internal;

import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import cz.kramolis.mega.runtime.Environment;
import cz.kramolis.mega.runtime.events.internal.BeforeShutdown;

@ApplicationScoped
public class EnvironmentImpl implements Environment {

    @Inject
    private Logger logger;

    @Inject
    private Universe universe;

    @Inject
    private Event<BeforeShutdown> beforeShutdownEvent;

    private long initNanoTime;

    private long startNanoTime;

    public EnvironmentImpl() {
        this.startNanoTime = System.nanoTime();
    }

    @Override
    public void shutdown() {
        beforeShutdownEvent.fire(new BeforeShutdown());
        universe.stop();
    }

    @Override
    public long getInitNanoTime() {
        return initNanoTime;
    }

    public void setInitNanoTime(long initNanoTime) {
        this.initNanoTime = initNanoTime;

        logger.info("Initialized in " + ((startNanoTime - initNanoTime) / 1000000) + " ms.");
    }

    @Override
    public long getStartNanoTime() {
        return startNanoTime;
    }

}
