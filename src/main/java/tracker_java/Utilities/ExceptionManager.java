package tracker_java.Utilities;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by cristian on 11/1/15.
 */
@Provider
public class ExceptionManager implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable e) {
        e.printStackTrace();
        
        if ( e instanceof WebApplicationException ){
            return ((WebApplicationException) e).getResponse();
        }

        // TODO have debug mode
        return ResponseStandardSet.serverErrorWithException(e);
    }
}
