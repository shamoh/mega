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

    public CDI<Object> init() {
        new RuntimeException("Who calls MainImpl.init?").printStackTrace();

        CDI<Object> cdi = CDI.getCDIProvider().initialize();
        EnvironmentImpl environment = cdi.select(EnvironmentImpl.class).get();

        environment.setStartNanoTime(getInitNanoTime());
        environment.setArgs(args);

        this.cdi = cdi;
        return cdi;
    }

    public CDI<Object> getCdi() {
        return cdi;
    }

    public void start() {
        if (cdi == null) {
            init();
        }
        Universe universe = cdi.select(Universe.class).get();
        universe.start();
    }

    public void run() {
        try (CDI<Object> cdi = init()) {
            start();
            Universe universe = cdi.select(Universe.class).get();

            try {
                universe.await();
            } catch (InterruptedException ex) {
                ex.printStackTrace(); //TODO
            }
        }
    }

    public void close() {
        cdi.close();
    }

    public long getInitNanoTime() {
        return initNanoTime;
    }

}
