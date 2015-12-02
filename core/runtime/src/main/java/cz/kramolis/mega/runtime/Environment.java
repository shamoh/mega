package cz.kramolis.mega.runtime;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import cz.kramolis.mega.runtime.internal.Universe;

@ApplicationScoped
public class Environment {

    @Inject
    private Universe universe;

    public void shutdown() {
//        TODO fireShutdownEvent
        universe.stop();
    }

}
