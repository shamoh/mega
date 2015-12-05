package cz.kramolis.mega.grizzly;

import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;

/**
 * Event...
 */
public interface BeforeHttpServerStart {

    HttpServer getHttpServer();

    void registerHttpHandler(HttpHandler httpHandler, String contextPath);

    void registerStaticContentHttpHandler(String docRoot, String contextPath);

}
