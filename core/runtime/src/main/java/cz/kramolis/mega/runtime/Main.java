package cz.kramolis.mega.runtime;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.logging.LogManager;

import javax.enterprise.inject.spi.CDI;

import cz.kramolis.mega.runtime.internal.EnvironmentImpl;
import cz.kramolis.mega.runtime.internal.Universe;

public class Main {

    mistake to test failing travis build ...

    public static final String PROP_SYSTEM_OUT_FILENAME = "mega.SystemOutFileName";
    public static final String PROP_SYSTEM_ERR_FILENAME = "mega.SystemErrFileName";

    public static void main(String[] args) throws Exception {
        final long initNanoTime = System.nanoTime();
        initLogging();
        try (CDI<Object> cdi = CDI.getCDIProvider().initialize();
                PrintStream systemOut = initSystemOut();
                PrintStream systemErr = initSystemErr(systemOut)) {
            Universe universe = cdi.select(Universe.class).get();
            EnvironmentImpl environment = cdi.select(EnvironmentImpl.class).get();
            environment.setStartNanoTime(initNanoTime);
            environment.setArgs(args != null ? Arrays.asList(args) : Collections.<String>emptyList());
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

    private static PrintStream initSystemOut() {
        PrintStream systemOut = createPrintStream(PROP_SYSTEM_OUT_FILENAME);
        if (systemOut != null) {
            System.setOut(systemOut);
        }
        return systemOut;
    }

    private static PrintStream initSystemErr(PrintStream systemOut) {
        String systemOutFileName = System.getProperty(PROP_SYSTEM_OUT_FILENAME);
        String systemErrFileName = System.getProperty(PROP_SYSTEM_ERR_FILENAME);
        PrintStream systemErr;
        if (Objects.equals(systemOutFileName, systemErrFileName)) {
            systemErr = systemOut;
        } else {
            systemErr = createPrintStream(PROP_SYSTEM_ERR_FILENAME);
        }
        if (systemErr != null) {
            System.setErr(systemErr);
        }
        return systemErr;
    }

    private static PrintStream createPrintStream(String propName) {
        String fileName = System.getProperty(propName);
        if (fileName != null) {
            try {
                return new PrintStream(fileName);
            } catch (FileNotFoundException ex) {
                throw new IllegalArgumentException(
                        "Problem to create " + fileName + " file, see value of " + propName + " system property.", ex);
            }
        }
        return null;
    }

}
