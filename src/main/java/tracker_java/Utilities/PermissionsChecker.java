package tracker_java.Utilities;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.glassfish.jersey.server.ContainerRequest;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import redis.clients.jedis.Jedis;
import tracker_java.Controllers.taskEndpointHandler;
import tracker_java.Models.HibernateUtil;

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

    @Around("@annotation(PermissionRequirements) ")
    public Object checkPermissionsIntercept(ProceedingJoinPoint joinPoint) throws Exception {
        System.out.println("checking permissions");
        ContainerRequest request = (ContainerRequest) joinPoint.getArgs()[0];
        /*
         token exist check
         get user of token
         get user permission on resource
         check is permission enough

         PERMISSION INFO
        0 - NONE
        1 - READ
        2 - COMMENT
        3 - WRITE

        TODO refactor to avoid hardcoding
        */

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
        String[] path = request.getPath(true).split(Pattern.quote("/"));
        String method = request.getMethod();
        System.out.format("request at %s : %s from userId %s%n", method, path, memberId);
        return checkUserPermissionAtPathAndMethod(memberId, path, method);
    }

    private boolean checkUserPermissionAtPathAndMethod(Integer memberId, String[] path, String method) {
        if (userIsAdmin(memberId)) {
            return true;
        }
        if ( path[0].equals("tasks") )
        {
            return handleProjectRights(path, memberId, method);
        }
        return false;
    }

    private boolean handleProjectRights(String[] path, Integer memberId, String method) {
        /*
        get project id to which tasks belongs
        get permission of user on project
        check against method
            GET - rights >= 1
            POST & path like ['tasks', <int>, "comments"] - rights >= 2
                else rights >= 3
         */
        return false;
    }

    private boolean userIsAdmin(Integer memberId) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        Query isAdminQuery = s.createQuery("select admin from MemberEntity where memberid = :memberid").setParameter("memberid", memberId);
        boolean isAdmin = false;
        try {
            isAdmin = (boolean) isAdminQuery.list().get(0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        s.flush();
        tx.commit();
        s.close();
        return isAdmin;
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
}

