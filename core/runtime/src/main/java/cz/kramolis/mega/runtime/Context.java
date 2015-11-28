package cz.kramolis.mega;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Context {

    public String getName() {
        return this.getClass().getName();
    }

}
