package cz.kramolis.mega.runtime;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public abstract class AbstractCdiBeanIT<T> extends AbstractCdiIT {

    private Class<? extends T> beanClass;

    public AbstractCdiBeanIT(Class<? extends T> beanClass) {
        this.beanClass = beanClass;
    }

    protected T instance() {
        return getCdi().select(beanClass).get();
    }

    @Test
    public void testIsNotNull() {
        assertThat(instance(), is(notNullValue()));
    }

}
