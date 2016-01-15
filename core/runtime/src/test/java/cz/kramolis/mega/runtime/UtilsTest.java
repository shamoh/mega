package cz.kramolis.mega.runtime;

import org.junit.Test;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class UtilsTest {

    @Test
    public void testGetAnnotationNoAnnotation() {
        assertThat(Utils.getAnnotation(UtilsTest.class, Deprecated.class), is(nullValue()));
    }

    @Test
    public void testGetAnnotationWithAnnotation() {
        assertThat(Utils.getAnnotation(My.class, Deprecated.class), instanceOf(Deprecated.class));
    }

    @Deprecated
    public interface My {
    }

}
