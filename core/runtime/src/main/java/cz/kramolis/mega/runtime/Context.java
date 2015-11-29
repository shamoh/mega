package cz.kramolis.mega.runtime;

import cz.kramolis.mega.runtime.cache.Cacheable;

/**
 * The class provides meta date about current application.
 * Each application have to implement the {@code Contect} interface
 * and annotate it with {@link javax.enterprise.context.ApplicationScoped}.
 */
public interface Context {

    @Cacheable
    default String getName() {
        return this.getClass().getSimpleName();
    }

    @Cacheable
    default String getRootPackage() {
        return this.getClass().getPackage().getName();
    }

}
