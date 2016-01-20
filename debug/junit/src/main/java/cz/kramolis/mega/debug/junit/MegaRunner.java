package cz.kramolis.mega.debug.junit;

import cz.kramolis.mega.runtime.internal.MainImpl;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

public class MegaRunner extends BlockJUnit4ClassRunner {

    private MainImpl main;

    public MegaRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
        this.main = new MainImpl();
    }

    @Override
    protected Statement withBeforeClasses(Statement statement) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                main.start();
                MegaRunner.super.withBeforeClasses(statement).evaluate();
            }
        };
    }

    @Override
    protected Statement withAfterClasses(Statement statement) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                MegaRunner.super.withAfterClasses(statement).evaluate();
                main.close();
            }
        };
    }

    @Override
    public Object createTest() throws Exception {
        Class<?> type = getTestClass().getJavaClass();
        return main.getCdi().select(type).get();
    }

}
