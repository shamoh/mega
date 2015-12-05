package cz.kramolis.mega.runtime;

import java.io.InputStream;
import java.util.logging.LogManager;

import javax.enterprise.inject.spi.CDI;

import cz.kramolis.mega.runtime.internal.EnvironmentImpl;
import cz.kramolis.mega.runtime.internal.Universe;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        final long initNanoTime = System.nanoTime();
        initLogging();
        try (CDI<Object> cdi = CDI.getCDIProvider().initialize()) {
            Universe universe = cdi.select(Universe.class).get();
            EnvironmentImpl environment = cdi.select(EnvironmentImpl.class).get();
            environment.setInitNanoTime(initNanoTime);
            universe.run();
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

}
