package cz.kramolis.mega.tests.helloworld;

import cz.kramolis.mega.runtime.Context;
import cz.kramolis.mega.runtime.Environment;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

//TODO test it
@ApplicationScoped
public class HelloWorldApp implements Context {

    private String message;

    private void onEnvironmentAvailable(@Observes Environment environment) {
        if (environment.getArgs().size() > 0) {
            for (String name : environment.getArgs()) {
                message = "Hello " + name + "!";
            }
        } else {
            message = "Hello World!";
        }
        System.out.println(message);

        environment.shutdown();
    }

    public String getMessage() {
        return message;
    }

}
