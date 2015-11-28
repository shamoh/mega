package cz.kramolis.mega.grizzly;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.deltaspike.core.api.config.ConfigProperty;

@ApplicationScoped
public class GrizzlyConfig {

    @Inject
    @ConfigProperty(name = "grizzly.host")
    private String host;

    @Inject
    @ConfigProperty(name = "grizzly.port")
    private int port;

    @Inject
    @ConfigProperty(name = "grizzly.staticContent.fileCacheEnabled")
    private boolean staticContentFileCacheEnabled;

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public boolean isStaticContentFileCacheEnabled() {
        return staticContentFileCacheEnabled;
    }

}
