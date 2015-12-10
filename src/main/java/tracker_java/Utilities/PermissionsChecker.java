package tracker_java.Utilities;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import redis.clients.jedis.Jedis;
import tracker_java.Models.HibernateUtil;

import javax.validation.constraints.Null;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.regex.Pattern;

import static tracker_java.Models.HibernateUtil.getOneItemFromQuery;

/**
 * Created by cristian on 11/11/15.
 */

public final class PermissionsChecker {

    public static boolean userIsAdmin(Integer memberId) {
        boolean isAdmin = false;

        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = s.beginTransaction();
            Query isAdminQuery = s.createQuery("select admin from MemberEntity where memberid = :memberid").setParameter("memberid", memberId);
            isAdmin = (boolean) isAdminQuery.list().get(0);
            s.flush();
            tx.commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return isAdmin;
    }

    private static Integer getUserIdFromToken(String token) {
        Integer userid = null;
        WebApplicationException thisExc = new WebApplicationException(ResponseStandardSet.noValidToken());

        try (Jedis redis = JedisPoolInstance.pool.getResource()) {
            userid = Integer.parseInt(redis.get(token));
            if (userid == null) {
                throw new NullPointerException();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw thisExc;
        }

        return userid;
    }

    private static String getTokenFromHeader(String bareHeader) {
        String token = null;
        WebApplicationException thisExc = new WebApplicationException(ResponseStandardSet.noValidToken());

        try {
            String[] typeAndToken = bareHeader.split(Pattern.quote(" "));
            if (typeAndToken[0].equals("Bearer") && typeAndToken[1].length() > 0) {
                token = typeAndToken[1];
            }
            if (token == null) {
                throw new NullPointerException();
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw thisExc;
        }

        return token;
    }

    public static int getUserIdFromAuthorization(String authorization) {
        return getUserIdFromToken(getTokenFromHeader(authorization));
    }

    /*
      get userid from token
      get projectid from itemid
      get memberproject junction entry
      >= 2
    */
    public static boolean checkPermissionComment(Integer itemid, String authorization) {
        Integer userid = getUserIdFromAuthorization(authorization);
        Integer projectId = getProjectIdFromTaskId(itemid);
        Integer permission = getPermissionFromUserIdProjectId(userid, projectId);
        return permission >= 2;
    }

    /*
      get userid from token
      get projectid from taskid
      get memberproject entry
      == 3
    */
    public static boolean checkPermissionWrite(int taskId, String authorization) {
        Integer userid = getUserIdFromAuthorization(authorization);
        Integer projectId = getProjectIdFromTaskId(taskId);
        Integer permission = getPermissionFromUserIdProjectId(userid, projectId);
        return permission == 3;
    }

    private static Integer getProjectIdFromTaskId(int taskId) {
        Integer projectid = null;
        WebApplicationException thisExc = new WebApplicationException(ResponseStandardSet.itemNotBelongsToProject());

        try {
            projectid = (Integer) getOneItemFromQuery(String.format("SELECT projectid FROM ItemEntity WHERE id = '%s'", taskId));
            if (projectid == null) {
                throw new NullPointerException();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw thisExc;
        }

        return projectid;
    }


    /*
      get userid from token
      get projectid from taskid
      get memberproject entry
      >= 1
    */
    public static boolean checkPermissionRead(int taskId, String authorization) {
        Integer userid = getUserIdFromAuthorization(authorization);
        Integer projectId = getProjectIdFromTaskId(taskId);
        Integer permission = getPermissionFromUserIdProjectId(userid, projectId);
        return permission >= 1;
    }

    private static Integer getPermissionFromUserIdProjectId(Integer userid, Integer projectId) {
        Integer perm = null;
        WebApplicationException thisExc = new WebApplicationException(ResponseStandardSet.noPermissionOnProject());

        try {
            perm = (Integer) getOneItemFromQuery(String.format("SELECT position FROM MemberprojectEntity WHERE memberid = '%s' AND projectid = '%s'", userid, projectId));
            if (perm == null) {
                throw new NullPointerException();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw thisExc;
        }

        return perm;
    }

    public static boolean checkWritePermissionToProject(Integer projectid, String authorization) {
        Integer userid = getUserIdFromAuthorization(authorization);
        Integer permission = getPermissionFromUserIdProjectId(userid, projectid);
        return permission >= 3;
    }
}

