package tracker_java.Utilities;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.glassfish.jersey.server.ContainerRequest;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.IllegalFormatException;
import java.util.regex.Pattern;

/**
 * Created by cristian on 11/11/15.
 */

@Aspect
public class PermissionsChecker {

    @Before("execution(* *.*(..)) && @annotation(PermissionRequirements) ")
    public Response checkPermissionsIntercept(JoinPoint joinPoint) throws Exception {

        System.out.println("checking permissions");
        ContainerRequest request = (ContainerRequest) joinPoint.getArgs()[0];
        String[] userNamePassword = AuthenticationHandler.INSTANCE.decode(request.getRequestHeader("authorization").get(0));
        System.out.println("user : " + userNamePassword[0]);
        String path = request.getPath(true);
        String[] splitPath = path.split(Pattern.quote("/"));

//        if ( true ) {
//            throw new UnauthorizedException();
//        }
        return Response.status(401).build();

    }

}

