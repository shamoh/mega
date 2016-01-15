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

/**
 *
 */
@RunWith(CdiTestRunner.class)
public class UtilsIT {

    @Inject
    private My my;

    @Test
    public void testGetAnnotationNoAnnotation() {
        assertThat(Utils.getAnnotation(UtilsIT.class, Deprecated.class), is(nullValue()));
    }

    @Test
    public void testGetAnnotationWithAnnotation() {
        assertThat(Utils.getAnnotation(My.class, Deprecated.class), instanceOf(Deprecated.class));
    }

    @Test
    public void testGetAnnotationSyntheticWithAnnotation() {
        assertThat(my.getClass().isSynthetic(), is(true));
        assertThat(my.getClass().getAnnotation(Deprecated.class), is(nullValue()));
        assertThat(Utils.getAnnotation(my.getClass(), Deprecated.class), instanceOf(Deprecated.class));
    }

    @Deprecated
    @ApplicationScoped
    public static class My {
    }

}
