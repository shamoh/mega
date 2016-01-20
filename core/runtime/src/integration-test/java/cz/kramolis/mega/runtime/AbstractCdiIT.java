package cz.kramolis.mega.runtime;

import javax.enterprise.inject.spi.CDI;

import cz.kramolis.mega.runtime.internal.MainImpl;

import org.junit.AfterClass;
import org.junit.BeforeClass;

public abstract class AbstractCdiIT {

    private static MainImpl main;

    @BeforeClass
    public static void initCdi() {
        main = new MainImpl();
        main.init();
        main.start();
    }

    @AfterClass
    public static void closeCdi() {
        main.close();
    }

    protected static CDI<Object> getCdi() {
        return main.getCdi();
    }

}
