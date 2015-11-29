package cz.kramolis.mega.runtime.internal;

import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import cz.kramolis.mega.runtime.Context;

@ApplicationScoped
public class Environment {

    @Inject
    private Logger logger;

    @Inject
    private Context context;

    @Inject
    private Event<Context> contextEvent;

    public void run() {
        logger.info("Application '" + context.getName() + "' is starting...");
        contextEvent.fire(context);
    }

}
