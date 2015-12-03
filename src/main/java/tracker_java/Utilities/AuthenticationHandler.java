package tracker_java.Utilities;

import org.glassfish.jersey.server.ContainerRequest;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;
import redis.clients.jedis.Jedis;
import tracker_java.Models.HibernateUtil;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;
import java.util.UUID;

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
    check if the userid already has a token
    if so, return that to the user
    else
    store newly generated token and return that
     */
    public static String saveToken(Integer userid) {
        try (Jedis redis = JedisPoolInstance.pool.getResource()) {
            String existingToken = redis.get(userid.toString());
            if (existingToken!=null) {
                return existingToken;
            }
            else {
                String theToken = UUID.randomUUID().toString().toUpperCase();
                redis.set(userid.toString(), theToken);
                redis.set(theToken, userid.toString());
                return theToken;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new WebApplicationException(Response.status(500).build());
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

