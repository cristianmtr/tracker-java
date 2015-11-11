package tracker_java.Utilities;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.*;


public class UnauthorizedException extends WebApplicationException {

    /**
     * Create a HTTP 401 (Unauthorized) exception.
     */
    public UnauthorizedException() {
        super(Response.status(401).build());
    }

    /**
     * Create a HTTP 404 (Not Found) exception.
     * @param message the String that is the entity of the 404 response.
     */
    public UnauthorizedException(String message) {
        super(Response.status(401).entity(message).type("text/plain").build());
    }

}