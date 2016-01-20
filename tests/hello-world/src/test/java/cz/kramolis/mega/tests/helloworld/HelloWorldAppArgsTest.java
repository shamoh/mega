package cz.kramolis.mega.tests.helloworld;

import javax.inject.Inject;

import cz.kramolis.mega.debug.junit.MegaRunner;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MegaRunner.class)
public class HelloWorldAppArgsTest {

    @Inject
    private HelloWorldApp helloWorldApp;

    @Ignore("TODO support MainImpl(args)")
    @Test
    public void testOnEnvironmentAvailable() {
        assertThat(helloWorldApp.getMessage(), is("Hello Libor!"));
    }

}
