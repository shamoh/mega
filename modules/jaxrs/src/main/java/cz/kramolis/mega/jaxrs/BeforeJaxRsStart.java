package cz.kramolis.mega.jaxrs;

import javax.ws.rs.core.Application;

/**
 * Event...
 */
public interface BeforeJaxRsStart {

    void registerApplication(Application application, String contextPath);

}
