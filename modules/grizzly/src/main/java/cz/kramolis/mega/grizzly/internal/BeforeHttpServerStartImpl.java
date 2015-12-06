package cz.kramolis.mega.grizzly.internal;

import cz.kramolis.mega.grizzly.BeforeHttpServerStart;
import cz.kramolis.mega.grizzly.GrizzlyConfig;
import cz.kramolis.mega.runtime.Context;

import org.glassfish.grizzly.http.server.CLStaticHttpHandler;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpHandlerRegistration;
import org.glassfish.grizzly.http.server.HttpServer;

/**
 * Event impl...
 */
public class BeforeHttpServerStartImpl implements BeforeHttpServerStart {

    private final HttpServer httpServer;
    private final Context context;
    private final GrizzlyConfig config;

    public BeforeHttpServerStartImpl(Context context, GrizzlyConfig config, HttpServer httpServer) {
        this.context = context;
        this.config = config;
        this.httpServer = httpServer;
    }

    @Override
    public HttpServer getHttpServer() {
        return httpServer;
    }

    @Override
    public void registerHttpHandler(final HttpHandler httpHandler, final String contextPath) {
        final String urlPattern = "/*";
        httpServer.getServerConfiguration().addHttpHandler(
                httpHandler,
                HttpHandlerRegistration.builder()
                        .contextPath(contextPath)
                        .urlPattern(urlPattern)
                        .build());
    }

    @Override
    public void registerStaticContentHttpHandler(final String docRoot, final String contextPath) {
        final boolean fileCacheEnabled = config.isStaticContentFileCacheEnabled();
        final CLStaticHttpHandler httpHandler = new CLStaticHttpHandler(
                context.getClass().getClassLoader(), docRoot);
        httpHandler.setFileCacheEnabled(fileCacheEnabled);
        registerHttpHandler(httpHandler, contextPath);
    }

}
