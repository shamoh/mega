package cz.kramolis.mega.grizzly;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import cz.kramolis.mega.runtime.Context;
import org.glassfish.grizzly.http.server.CLStaticHttpHandler;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpHandlerRegistration;

@ApplicationScoped
public class GrizzlyHttpServerUtils {

    @Inject
    private Context context;

    @Inject
    private GrizzlyConfig config;

    @Inject
    private HttpServerHolder httpServerHolder;

    public void registerHttpHandler(final HttpHandler httpHandler, final String contextPath) {
        final String urlPattern = "/*";
        httpServerHolder.getHttpServer().getServerConfiguration().addHttpHandler(
                httpHandler,
                HttpHandlerRegistration.builder()
                        .contextPath(contextPath)
                        .urlPattern(urlPattern)
                        .build());
    }

    public void registerStaticContentHttpHandler(final String docRoot, final String contextPath) {
        final boolean fileCacheEnabled = config.isStaticContentFileCacheEnabled();
        final CLStaticHttpHandler httpHandler = new CLStaticHttpHandler(
                context.getClass().getClassLoader(), docRoot);
        httpHandler.setFileCacheEnabled(fileCacheEnabled);
        registerHttpHandler(httpHandler, contextPath);
    }

}
