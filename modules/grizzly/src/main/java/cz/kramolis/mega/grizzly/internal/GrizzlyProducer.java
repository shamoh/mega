package cz.kramolis.mega.grizzly.internal;

import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import cz.kramolis.mega.grizzly.BeforeHttpServerStart;
import cz.kramolis.mega.grizzly.GrizzlyConfig;
import cz.kramolis.mega.runtime.Context;
import cz.kramolis.mega.runtime.Environment;
import cz.kramolis.mega.runtime.Holder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;

@ApplicationScoped
public class GrizzlyProducer {

    @Inject
    private Event<BeforeHttpServerStart> beforeHttpServerStartEvent;

    private void onEnvironmentAvailable(@Observes Environment environment,
                                        Context context, GrizzlyConfig config, Holder<HttpServer> httpServer)
            throws IOException {
        BeforeHttpServerStart beforeHttpServerStart = new BeforeHttpServerStartImpl(context, config, httpServer.get());
        beforeHttpServerStartEvent.fire(beforeHttpServerStart);
        beforeHttpServerStart.getHttpServer().start();
    }

    @Produces
    @ApplicationScoped
    private Holder<HttpServer> createHttpServerHolder(GrizzlyConfig config) {
        // TODO: is it necessary to create BeforeHttpServerStart instance by @Produces method?
        // TODO: this method should just return HttpServer

        final HttpServer httpServer = new HttpServer();

        Runtime.getRuntime().addShutdownHook(new Thread(httpServer::shutdownNow));

        final NetworkListener listener = new NetworkListener("grizzly", config.getHost(), config.getPort());
        httpServer.addListener(listener);

        return () -> httpServer;
    }

}
