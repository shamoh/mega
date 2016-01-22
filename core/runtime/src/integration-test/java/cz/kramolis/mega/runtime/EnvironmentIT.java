package cz.kramolis.mega.runtime;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class EnvironmentIT extends AbstractCdiBeanIT<Environment> {

    public EnvironmentIT() {
        super(Environment.class);
    }

    @Test
    public void testGetArgsNotNull() {
        assertThat(instance().getArgs(), is(notNullValue()));
    }

    @Test
    public void testGetStartNanoTimeNotSet() {
        assertThat(instance().getStartNanoTime(), is(not(-1)));
    }

    @Test
    public void testGetRunNanoTimeNotSet() {
        assertThat(instance().getRunNanoTime(), is(not(-1)));
    }

}
