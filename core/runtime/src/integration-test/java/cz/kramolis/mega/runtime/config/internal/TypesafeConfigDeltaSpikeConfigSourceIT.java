package cz.kramolis.mega.runtime.config.internal;

import cz.kramolis.mega.runtime.AbstractCdiIT;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class TypesafeConfigDeltaSpikeConfigSourceIT extends AbstractCdiIT {

    @Test
    public void testExistingKey() {
        MyConfigBean myConfigBean = getCdi().select(MyConfigBean.class).get();
        assertThat(myConfigBean.getKey1(), is("value1"));
    }

    @Test
    public void testNotExistingKey() {
        MyConfigBean myConfigBean = getCdi().select(MyConfigBean.class).get();
        assertThat(myConfigBean.getUnknown(), is(nullValue()));
    }

}
