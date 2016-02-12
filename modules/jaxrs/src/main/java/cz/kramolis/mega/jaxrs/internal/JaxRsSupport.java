package cz.kramolis.mega.jaxrs.internal;

import cz.kramolis.mega.grizzly.BeforeHttpServerStart;
import cz.kramolis.mega.jaxrs.BeforeJaxRsStart;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class JaxRsSupport {

    @Inject
    private Event<BeforeJaxRsStart> beforeJaxRsRegisterEvent;

    private void onHttpServerAvailable(@Observes BeforeHttpServerStart beforeHttpServerStart) {
        BeforeJaxRsStart beforeJaxRsStart = new BeforeJaxRsStartImpl(beforeHttpServerStart);
        beforeJaxRsRegisterEvent.fire(beforeJaxRsStart);
    }

}
