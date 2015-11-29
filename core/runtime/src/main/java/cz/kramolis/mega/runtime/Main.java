package cz.kramolis.mega.runtime;

import java.io.InputStream;
import java.util.logging.LogManager;

import javax.enterprise.inject.spi.CDI;

import cz.kramolis.mega.runtime.internal.Environment;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        initLogging();
        try (CDI<Object> cdi = CDI.getCDIProvider().initialize()) {
            Environment environment = cdi.select(Environment.class).get();
            environment.run();
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
