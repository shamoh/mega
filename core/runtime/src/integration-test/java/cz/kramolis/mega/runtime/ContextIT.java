package cz.kramolis.mega.runtime;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class ContextIT extends AbstractCdiBeanIT<Context> {

    public ContextIT() {
        super(Context.class);
    }

    @Test
    public void testGetNameNotNull() {
        assertThat(instance().getName(), is(notNullValue()));
    }

    @Test
    public void testGetRootPackageNotNull() {
        assertThat(instance().getRootPackage(), is(notNullValue()));
    }

}
