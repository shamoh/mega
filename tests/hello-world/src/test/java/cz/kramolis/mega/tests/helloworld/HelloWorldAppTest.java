package cz.kramolis.mega.tests.helloworld;

import cz.kramolis.mega.debug.junit.MegaRunner;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MegaRunner.class)
public class HelloWorldAppTest {

    @Inject
    private HelloWorldApp helloWorldApp;

    @Test
    public void testOnEnvironmentAvailable() {
        assertThat(helloWorldApp.getMessage(), is("Hello World!"));
    }

}
