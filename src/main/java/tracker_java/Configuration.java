package tracker_java;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import tracker_java.Controllers.taskEndpointHandler;
import tracker_java.Utilities.ExceptionManager;
import tracker_java.Utilities.MyObjectMapperProvider;

/**
 * Created by cristian on 10/26/15.
 */
public class Configuration extends ResourceConfig {
    public Configuration() {
        super(
                taskEndpointHandler.class,
                // register Jackson ObjectMapper resolver
                MyObjectMapperProvider.class,
                JacksonFeature.class,
                // register custom exceptions handler
                ExceptionManager.class
        );
    }
}
