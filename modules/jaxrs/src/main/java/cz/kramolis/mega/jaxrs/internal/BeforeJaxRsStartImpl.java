package cz.kramolis.mega.jaxrs.internal;

import javax.ws.rs.core.Application;

import cz.kramolis.mega.grizzly.BeforeHttpServerStart;
import cz.kramolis.mega.jaxrs.BeforeJaxRsStart;

import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpContainer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpContainerProvider;
import org.glassfish.jersey.server.ResourceConfig;

public class BeforeJaxRsStartImpl implements BeforeJaxRsStart {

    private BeforeHttpServerStart beforeHttpServerStart;

    public BeforeJaxRsStartImpl(BeforeHttpServerStart beforeHttpServerStart) {
        this.beforeHttpServerStart = beforeHttpServerStart;
    }

    @Override
    public void registerApplication(Application application, String contextPath) {
        final HttpHandler httpHandler = new GrizzlyHttpContainerProvider()
                .createContainer(GrizzlyHttpContainer.class, ResourceConfig.forApplication(application));
        beforeHttpServerStart.registerHttpHandler(httpHandler, contextPath);
    }

}
