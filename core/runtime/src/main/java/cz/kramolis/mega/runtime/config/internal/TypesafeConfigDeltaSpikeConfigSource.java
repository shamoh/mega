package cz.kramolis.mega.runtime.config.internal;

import java.util.Map;
import java.util.stream.Collectors;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.deltaspike.core.spi.config.ConfigSource;

public class TypesafeConfigDeltaSpikeConfigSource implements ConfigSource {

    private static final int ORDINAL = 50;

    private final Config config;

    public TypesafeConfigDeltaSpikeConfigSource() {
        this.config = ConfigFactory.load();
    }

    @Override
    public int getOrdinal() {
        // If a custom implementation should be invoked after the default implementations, use an ordinal-value < 100
        return ORDINAL;
    }

    @Override
    public Map<String, String> getProperties() {
        return config.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().toString()));
    }

    @Override
    public String getPropertyValue(String key) {
        return config.hasPath(key) ? config.getString(key) : null;
    }

    @Override
    public String getConfigName() {
        return "Typesafe Config";
    }

    @Override
    public boolean isScannable() {
        return true;
    }

}
