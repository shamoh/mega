package cz.kramolis.mega.runtime.internal;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Optional;
import java.util.logging.LogManager;

public final class LoggingSupport {

    private LoggingSupport() {
    }

    public static void initLogging() {
        //TODO use config to configure this
        final String loggingProperties = "/logging.properties";
        if (LoggingSupport.class.getResource(loggingProperties) != null) {
            try (InputStream inputStream = LoggingSupport.class.getResourceAsStream(loggingProperties)) {
                LogManager.getLogManager().readConfiguration(inputStream);
            } catch (Exception ex) {
                ex.printStackTrace(); //TODO
            }
        }
    }

    //
    // system out|err
    //

    public static void runWithInitSystemOutErr(String systemOutFileName, String systemErrFileName, Runnable runner) {
        try (PrintStream systemOut = initPrintStream(systemOutFileName, null, null);
                PrintStream systemErr = initPrintStream(systemErrFileName, systemOutFileName, systemOut)) {
            Optional.ofNullable(systemOut).ifPresent(System::setOut);
            Optional.ofNullable(systemErr).ifPresent(System::setErr);

            runner.run();
        }
    }

    private static PrintStream initPrintStream(String fileName, String secondFileName, PrintStream secondPrintStream) {
        if (fileName != null) {
            if (fileName.equals(secondFileName)) {
                return secondPrintStream;
            } else {
                try {
                    return new PrintStream(fileName, Charset.defaultCharset().name());
                } catch (FileNotFoundException ex) {
                    throw new IllegalArgumentException("Problem to create " + fileName + " file", ex);
                } catch (UnsupportedEncodingException ex) {
                    throw new IllegalStateException("Problem to create " + fileName + " file.", ex);
                }
            }
        }
        return null;
    }

}
