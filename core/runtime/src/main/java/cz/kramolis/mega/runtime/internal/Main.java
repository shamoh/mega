package cz.kramolis.mega.internal;

import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;

import cz.kramolis.mega.Context;

@ApplicationScoped
public class Main {

    @Inject
    private Logger logger;

    @Inject
    private Context context;

    @Inject
    private Event<Context> contextEvent;

    public static void main(String[] args) throws InterruptedException {
        initLogging();
        try (CDI<Object> cdi = CDI.getCDIProvider().initialize()) {
            Main main = cdi.select(Main.class).get();
            main.run();
            Thread.currentThread().join();
        }
    }

    private static void initLogging() {
        final String loggingProperties = "/logging.properties";
        if (Main.class.getResource(loggingProperties) != null) {
            try (InputStream inputStream = Main.class.getResourceAsStream(loggingProperties)) {
                LogManager.getLogManager().readConfiguration(inputStream);
            } catch (Exception ex) {
                ex.printStackTrace(); //TODO
            }
        }
    }

    public void run() {
        logger.info("Application '" + context.getName() + "' is starting...");
        contextEvent.fire(context);
    }

}
