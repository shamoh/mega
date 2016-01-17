package cz.kramolis.mega.runtime.internal;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.inject.spi.CDI;

public final class MainImpl {

    private final List<String> args;
    private final long initNanoTime;
    private CDI<Object> cdi;

    public MainImpl(String... args) {
        this.args = Arrays.asList(args);
        this.initNanoTime = System.nanoTime();
    }

    public void initCdi() {
        CDI<Object> cdi = CDI.getCDIProvider().initialize();
        EnvironmentImpl environment = cdi.select(EnvironmentImpl.class).get();

        environment.setStartNanoTime(getInitNanoTime());
        environment.setArgs(args);

        this.cdi = cdi;
    }

    public CDI<Object> getCdi() {
        if (cdi == null) {
            initCdi();
        }
        return cdi;
    }

    public void run() {
        try (CDI<Object> cdi = getCdi()) {
            Universe universe = cdi.select(Universe.class).get();

            try {
                universe.run();
            } catch (InterruptedException ex) {
                ex.printStackTrace(); //TODO
            }
        }
    }

    public long getInitNanoTime() {
        return initNanoTime;
    }

}
