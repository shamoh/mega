package cz.kramolis.mega.runtime.config.internal;

import javax.inject.Inject;

import org.apache.deltaspike.core.api.config.ConfigProperty;

public class MyConfigBean {

    @Inject @ConfigProperty(name = "key1")
    private String key1;

    @Inject @ConfigProperty(name = "unknown")
    private String unknown;

    public String getKey1() {
        return key1;
    }

    public String getUnknown() {
        return unknown;
    }

}
