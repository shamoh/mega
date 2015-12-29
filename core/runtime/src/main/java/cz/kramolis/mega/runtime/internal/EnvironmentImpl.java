package cz.kramolis.mega.runtime.internal;

import java.util.List;
import java.util.Objects;
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

    private List<String> args;

    private long startNanoTime;

    private long runNanoTime;

    public EnvironmentImpl() {
    }

    @Override
    public void shutdown() {
        beforeShutdownEvent.fire(new BeforeShutdown());
        universe.stop();
    }

    @Override
    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        Objects.requireNonNull(args);

        this.args = args;
    }

    @Override
    public long getStartNanoTime() {
        return startNanoTime;
    }

    public void setStartNanoTime(long startNanoTime) {
        this.startNanoTime = startNanoTime;
    }

    @Override
    public long getRunNanoTime() {
        return runNanoTime;
    }

    public void initRunNanoTime() {
        this.runNanoTime = System.nanoTime();

        logger.info("Initialized in " + ((runNanoTime - startNanoTime) / 1000000) + " ms.");
    }

}
