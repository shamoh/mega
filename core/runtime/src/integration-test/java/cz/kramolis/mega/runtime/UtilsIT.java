package cz.kramolis.mega.runtime;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(CdiTestRunner.class)
public class UtilsIT {

    @Inject
    private MyBean myBean;

    @Test
    public void testGetAnnotationNoAnnotation() {
        assertThat(Utils.getAnnotation(UtilsIT.class, Deprecated.class), is(nullValue()));
    }

    @Test
    public void testGetAnnotationWithAnnotation() {
        assertThat(Utils.getAnnotation(MyBean.class, Deprecated.class), instanceOf(Deprecated.class));
    }

    @Test
    public void testGetAnnotationSyntheticWithAnnotation() {
        assertThat(myBean.getClass().isSynthetic(), is(true));
        assertThat(myBean.getClass().getAnnotation(Deprecated.class), is(nullValue()));
        assertThat(Utils.getAnnotation(myBean.getClass(), Deprecated.class), instanceOf(Deprecated.class));
    }

    @Deprecated
    @ApplicationScoped
    public static class MyBean {
    }

}
