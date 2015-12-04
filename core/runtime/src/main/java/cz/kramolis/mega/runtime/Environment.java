package cz.kramolis.mega.runtime;

import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import cz.kramolis.mega.runtime.events.internal.BeforeShutdown;
import cz.kramolis.mega.runtime.internal.Universe;

@ApplicationScoped
public class Environment {

    @Inject
    private Logger logger;

    @Inject
    private Universe universe;

    @Inject
    private Event<BeforeShutdown> beforeShutdownEvent;

    private long initNanoTime;

    private long startNanoTime;

    public Environment() {
        this.startNanoTime = System.nanoTime();
    }

    public void shutdown() {
        beforeShutdownEvent.fire(new BeforeShutdown());
        universe.stop();
    }

    public long getInitNanoTime() {
        return initNanoTime;
    }

    public void setInitNanoTime(long initNanoTime) {
        this.initNanoTime = initNanoTime;

        logger.info("Initialized in " + ((startNanoTime - initNanoTime) / 1000000) + " ms.");
    }

    public long getStartNanoTime() {
        return startNanoTime;
    }

}
