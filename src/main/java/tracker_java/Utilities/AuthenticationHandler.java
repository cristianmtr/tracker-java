package tracker_java.Utilities;

import org.glassfish.jersey.server.ContainerRequest;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;
import redis.clients.jedis.Jedis;
import tracker_java.Models.HibernateUtil;

import javax.xml.bind.DatatypeConverter;

/**
 * Created by cristian on 11/10/15.
 */
public final class AuthenticationHandler {

    // all methods are static since they do not store any state in the class
    public static String[] decode(String auth) {
        auth = auth.replaceFirst("[B|b]asic ", "");

        byte[] decodedBytes = DatatypeConverter.parseBase64Binary(auth);

        if (decodedBytes == null || decodedBytes.length == 0) {
            return null;
        }

        return new String(decodedBytes).split(":", 2);
    }

    public static boolean checkUserNamePassword(String username, String password) throws Exception{
        try {
            String hashed = null;
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = s.beginTransaction();
            Query q = s.createQuery("select password from MemberEntity where username = :username").setParameter("username", username);
            try {
                hashed = (String) q.list().get(0);
            } catch (Throwable e) {
                e.printStackTrace();
            }
            s.flush();
            tx.commit();
            s.close();

            return hashed != null && BCrypt.checkpw(password, hashed);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /*
    stores the token as a key, with userid as value;
    stores the userid as a key, with token as value;
    check and delete if a userid was already assoc. with a previous token;
     */
    public static void saveToken(String theToken, Integer userid) {
        try {
            Jedis redis = JedisPoolInstance.pool.getResource();
            redis.set(theToken, userid.toString());
            if (redis.get(userid.toString())!=null) {
                redis.del(redis.get(userid.toString()));
            }
            redis.set(userid.toString(), theToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Integer getUserIdFromUsername(String username) throws Exception {
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = s.beginTransaction();
            Query q = s.createQuery("select memberid from MemberEntity where username = :username").setParameter("username", username);
            Integer userId = (Integer) q.list().get(0);
            s.flush();
            tx.commit();
            s.close();
            return userId;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}

