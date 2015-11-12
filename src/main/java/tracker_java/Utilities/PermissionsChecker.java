package tracker_java.Utilities;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.glassfish.jersey.server.ContainerRequest;
import redis.clients.jedis.Jedis;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by cristian on 11/11/15.
 */

@Aspect
public final class PermissionsChecker {

    @Around("execution(* *.*(..)) && @annotation(PermissionRequirements) ")
    public Object checkPermissionsIntercept(ProceedingJoinPoint joinPoint) throws Exception {
        System.out.println("checking permissions");
        ContainerRequest request = (ContainerRequest) joinPoint.getArgs()[0];
        // token exist check
        // get user of token
        // get user permission on resource
        // check is permission enough
        if (this.userOfTokenHasPermission(request)) {
            try {
                return joinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        };
        return Response.status(401).build();
    }

    private boolean userOfTokenHasPermission(ContainerRequest request) {
        String token = getToken(request);
        Integer memberId = getUserIdFromToken(token);
        String path = request.getPath(true);
        System.out.format("request at %s from userId %s%n", path, memberId);

        return false;
    }

    private Integer getUserIdFromToken(String token) {
        try {
            Jedis redis = JedisPoolInstance.pool.getResource();
            return Integer.parseInt(redis.get(token));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private String getToken(ContainerRequest request) {
        try {
            String bareHeader = request.getRequestHeader("authorization").get(0);
            // should be ["Bearer", $token]
            String[] typeAndToken = bareHeader.split(Pattern.quote(" "));
            if (typeAndToken[0].equals("Bearer") && typeAndToken[1].length() > 0) {
                return typeAndToken[1];
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        // otherwise Bad Request
        throw new WebApplicationException(Response.status(400).build());
    }

    private String decodeToken(ContainerRequest request) {
        return null;
    }

}

