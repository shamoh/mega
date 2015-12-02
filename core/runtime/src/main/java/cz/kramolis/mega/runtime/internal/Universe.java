package cz.kramolis.mega.runtime.internal;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import cz.kramolis.mega.runtime.Context;

@ApplicationScoped
public class Universe {

    private final CountDownLatch latch = new CountDownLatch(1);

    @Inject
    private Logger logger;
    @Inject
    private Context context;
    @Inject
    private Event<Context> contextEvent;

    public void run() throws InterruptedException {
        logger.info("Application '" + context.getName() + "' is starting...");
        contextEvent.fire(context);
        latch.await();
    }

    public void stop() {
        if (logger.isLoggable(Level.FINEST)) {
            logger.log(Level.FINEST, "Who calls shutdown?", new RuntimeException("Not issue, just for debug purposes."));
        }
        latch.countDown();
    }

}