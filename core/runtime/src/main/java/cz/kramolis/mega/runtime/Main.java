package cz.kramolis.mega.runtime;

import cz.kramolis.mega.runtime.internal.ConfigSupport;
import cz.kramolis.mega.runtime.internal.LoggingSupport;
import cz.kramolis.mega.runtime.internal.MainImpl;

public final class Main {

    public static final String PROP_SYSTEM_OUT_FILENAME = "mega.SystemOutFileName";
    public static final String PROP_SYSTEM_ERR_FILENAME = "mega.SystemErrFileName";

    public static void main(String[] args) throws Exception {
        ConfigSupport.initConfig();
        LoggingSupport.initLogging();

        MainImpl main = new MainImpl(args);

        LoggingSupport.runWithInitSystemOutErr(
                System.getProperty(PROP_SYSTEM_OUT_FILENAME),
                System.getProperty(PROP_SYSTEM_ERR_FILENAME),
                main::run);
    }

}
