package cz.kramolis.mega.jaxrs.internal;

import java.util.Arrays;
import java.util.logging.Logger;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import cz.kramolis.mega.grizzly.BeforeHttpServerStart;
import cz.kramolis.mega.jaxrs.BeforeJaxRsStart;
import cz.kramolis.mega.runtime.Utils;

import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpContainer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpContainerProvider;
import org.glassfish.jersey.server.ResourceConfig;

public class BeforeJaxRsStartImpl implements BeforeJaxRsStart {

    private static final Logger LOGGER = Logger.getLogger(BeforeJaxRsStartImpl.class.getName());

    private BeforeHttpServerStart beforeHttpServerStart;

    public BeforeJaxRsStartImpl(BeforeHttpServerStart beforeHttpServerStart) {
        this.beforeHttpServerStart = beforeHttpServerStart;
    }

    @Override
    public void registerApplication(Application application, String contextPath) {
        LOGGER.fine("registerApplication @ " + contextPath + " : " + application);
        final HttpHandler httpHandler = new GrizzlyHttpContainerProvider()
                .createContainer(GrizzlyHttpContainer.class, ResourceConfig.forApplication(application));
        if (contextPath == null) {
            LOGGER.fine("    application.class: " + application.getClass());
            LOGGER.fine("    application.class.annotations: " + Arrays.asList(application.getClass().getAnnotations()));
            ApplicationPath applicationPath = Utils.getAnnotation(application.getClass(), ApplicationPath.class);
            LOGGER.fine("    applicationPath: " + applicationPath);
            if (applicationPath != null && applicationPath.value() != null) {
                contextPath = applicationPath.value();
                LOGGER.fine("    contextPath: " + contextPath);
            }
        }
        if (contextPath != null && !contextPath.startsWith("/")) {
            contextPath = "/" + contextPath;
        }
        beforeHttpServerStart.registerHttpHandler(httpHandler, contextPath);
    }

}
