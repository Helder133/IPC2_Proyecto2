package ipc2_proyecto2.backend_proyecto2.rest.api.app;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Configures Jakarta RESTful Web Services for the application.
 * @author Juneau
 */
@ApplicationPath("api/v1")
public class JakartaRestConfiguration extends ResourceConfig {
    
    public JakartaRestConfiguration() {
        packages("ipc2_proyecto2.backend_proyecto2.rest.api.app.resources").register(MultiPartFeature.class);
    }
}
