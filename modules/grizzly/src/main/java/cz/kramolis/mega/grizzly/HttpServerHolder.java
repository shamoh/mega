package cz.kramolis.mega.grizzly;

import org.glassfish.grizzly.http.server.HttpServer;

public class HttpServerHolder {

    private final HttpServer httpServer;

    public HttpServerHolder(HttpServer httpServer) {
        this.httpServer = httpServer;
    }

    public HttpServer getHttpServer() {
        return httpServer;
    }

}
