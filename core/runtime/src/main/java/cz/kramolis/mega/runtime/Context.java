package cz.kramolis.mega.runtime;

/**
 * The class provides meta date about current application.
 * Each application have to implement the {@code Context} interface
 * and annotate it with {@link javax.enterprise.context.ApplicationScoped}.
 */
public interface Context {

    //@Immutable
    default String getName() {
        return this.getClass().getSimpleName();
    }

    //@Immutable
    default String getRootPackage() {
        return this.getClass().getPackage().getName();
    }

}
