package tracker_java.Utilities;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import redis.clients.jedis.Jedis;
import tracker_java.Models.HibernateUtil;

import java.util.regex.Pattern;

import static tracker_java.Models.HibernateUtil.getOneItemFromQuery;

/**
 * Created by cristian on 11/11/15.
 */

public final class PermissionsChecker {

    private boolean userIsAdmin(Integer memberId) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        Query isAdminQuery = s.createQuery("select admin from MemberEntity where memberid = :memberid").setParameter("memberid", memberId);
        boolean isAdmin = false;
        try {
            isAdmin = (boolean) isAdminQuery.list().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        s.flush();
        tx.commit();
        s.close();
        return isAdmin;
    }

    private static Integer getUserIdFromToken(String token) {
        try {
            Jedis redis = JedisPoolInstance.pool.getResource();
            return Integer.parseInt(redis.get(token));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getTokenFromHeader(String bareHeader) {
        String[] typeAndToken = bareHeader.split(Pattern.quote(" "));
        if (typeAndToken[0].equals("Bearer") && typeAndToken[1].length() > 0) {
            return typeAndToken[1];
        }
        return null;
    }

    /*
    get userid from token
    get projectid from itemid
    get memberproject junction entry
    >= 2
     */
    public static boolean checkPermissionComment(Integer itemid, String authorization) {
        Integer userid = getUserIdFromToken(getTokenFromHeader(authorization));
        Integer projectId = (Integer) getOneItemFromQuery(String.format("SELECT projectid FROM ItemEntity WHERE itemid = '%s'",itemid));
        Integer permission = (Integer) getOneItemFromQuery(String.format("SELECT position FROM MemberprojectEntity WHERE memberid = '%s' AND projectid = '%s'", userid, projectId));
        return permission >= 2;
    }

    /*
    get userid from token
    get projectid from taskid
    get memberproject entry
    == 3
     */
    public static boolean checkPermissionWrite(int taskId, String authorization) {
        Integer userid = getUserIdFromToken(getTokenFromHeader(authorization));
        Integer projectId = (Integer) getOneItemFromQuery(String.format("SELECT projectid FROM ItemEntity WHERE itemid = '%s'",taskId));
        Integer permission = (Integer) getOneItemFromQuery(String.format("SELECT position FROM MemberprojectEntity WHERE memberid = '%s' AND projectid = '%s'", userid, projectId));
        return permission == 3;
    }

    /*
    get userid from token
    get projectid from taskid
    get memberproject entry
    >= 1
     */
    public static boolean checkPermissionRead(int taskId, String authorization) {
        Integer userid = getUserIdFromToken(getTokenFromHeader(authorization));
        Integer projectId = (Integer) getOneItemFromQuery(String.format("SELECT projectid FROM ItemEntity WHERE itemid = '%s'",taskId));
        Integer permission = (Integer) getOneItemFromQuery(String.format("SELECT position FROM MemberprojectEntity WHERE memberid = '%s' AND projectid = '%s'", userid, projectId));
        return permission >= 1;
    }
}

