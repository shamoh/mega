package cz.kramolis.mega.runtime.config.internal;

import javax.inject.Inject;

import org.apache.deltaspike.core.api.config.ConfigProperty;
import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(CdiTestRunner.class)
public class TypesafeConfigDeltaSpikeConfigSourceIT {

    @Inject @ConfigProperty(name = "key1")
    private String key1;

    @Inject @ConfigProperty(name = "unknown")
    private String unknown;

    @Test
    public void testExistingKey() {
        assertThat(key1, is("value1"));
    }

    @Test
    public void testNotExistingKey() {
        assertThat(unknown, is(nullValue()));
    }

}
