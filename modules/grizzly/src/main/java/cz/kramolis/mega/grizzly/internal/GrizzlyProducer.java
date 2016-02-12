package cz.kramolis.mega.grizzly.internal;

import cz.kramolis.mega.grizzly.BeforeHttpServerStart;
import cz.kramolis.mega.grizzly.GrizzlyConfig;
import cz.kramolis.mega.runtime.Context;
import cz.kramolis.mega.runtime.Environment;

import java.io.IOException;
import java.util.function.Supplier;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;

@ApplicationScoped
public class GrizzlyProducer {

    @Inject
    private Event<BeforeHttpServerStart> beforeHttpServerStartEvent;

    private void onEnvironmentAvailable(@Observes Environment environment,
                                        Context context, GrizzlyConfig config, Supplier<HttpServer> httpServer)
            throws IOException {
        BeforeHttpServerStart beforeHttpServerStart = new BeforeHttpServerStartImpl(context, config, httpServer.get());
        beforeHttpServerStartEvent.fire(beforeHttpServerStart);
        beforeHttpServerStart.getHttpServer().start();
    }

    @Produces
    @ApplicationScoped
    private Supplier<HttpServer> createHttpServerHolder(GrizzlyConfig config) {
        // TODO: is it necessary to create BeforeHttpServerStart instance by @Produces method?
        // TODO: this method should just return HttpServer

        final HttpServer httpServer = new HttpServer();

        final NetworkListener listener = new NetworkListener("grizzly", config.getHost(), config.getPort());
        httpServer.addListener(listener);

        return () -> httpServer;
    }

    public void close(@Disposes Supplier<HttpServer> httpServerHolder) {
        httpServerHolder.get().shutdownNow();
    }

}
