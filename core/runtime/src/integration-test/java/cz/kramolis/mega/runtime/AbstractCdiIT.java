package cz.kramolis.mega.runtime;

import javax.enterprise.inject.spi.CDI;

import cz.kramolis.mega.runtime.internal.MainImpl;

import org.junit.AfterClass;
import org.junit.BeforeClass;

public abstract class AbstractCdiIT {

    private static CDI<Object> cdi;

    @BeforeClass
    public static void initCdi() {
        MainImpl main = new MainImpl();
        main.initCdi();
        cdi = main.getCdi();
    }

    @AfterClass
    public static void closeCdi() {
        cdi.close();
    }

    protected static CDI<Object> getCdi() {
        return cdi;
    }

}
