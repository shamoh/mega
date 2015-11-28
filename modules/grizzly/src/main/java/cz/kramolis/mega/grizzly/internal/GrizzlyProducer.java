package cz.kramolis.mega.grizzly.internal;

import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import cz.kramolis.mega.Context;
import cz.kramolis.mega.grizzly.GrizzlyConfig;
import cz.kramolis.mega.grizzly.HttpServerHolder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;

@ApplicationScoped
public class GrizzlyProducer {

    @Inject
    private Event<HttpServerHolder> httpServerHolderEvent;

    @Produces
    @ApplicationScoped
    private HttpServerHolder createHttpServerHolder(GrizzlyConfig config) {
        final HttpServer httpServer = new HttpServer();

        Runtime.getRuntime().addShutdownHook(new Thread(httpServer::shutdownNow));

        final NetworkListener listener = new NetworkListener("grizzly", config.getHost(), config.getPort());
        httpServer.addListener(listener);

        return new HttpServerHolder(httpServer);
    }

    public void onContextAvailable(@Observes Context context, HttpServerHolder httpServerHolder) throws IOException {
        httpServerHolderEvent.fire(httpServerHolder);
        httpServerHolder.getHttpServer().start();
    }

}
