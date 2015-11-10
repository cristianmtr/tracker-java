package tracker_java.Utilities;

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
public enum AuthenticationHandler {
    INSTANCE;
    public String[] decode(String auth) {
        auth = auth.replaceFirst("[B|b]asic ", "");

        byte[] decodedBytes = DatatypeConverter.parseBase64Binary(auth);

        if(decodedBytes == null || decodedBytes.length == 0){
            return null;
        }

        return new String(decodedBytes).split(":", 2);
    }

    public boolean checkUserNamePassword(String username, String password) {
        String hashed = null;

        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        Query q = s.createQuery("select password from MemberEntity where username = :username").setParameter("username", username);
        try {
            hashed = (String) q.list().get(0);
        }
        catch (Throwable e) {
            e.printStackTrace();
        }
        s.flush();
        tx.commit();
        s.close();

        return hashed != null && BCrypt.checkpw(password, hashed);
    }

    public boolean checkTokenExists(String theToken) {
        Jedis redis = new Jedis("localhost");
        return redis.exists(theToken).equals(1);
    }

    public boolean checkTokenUsername(String theToken, String userName) {
        Jedis redis = new Jedis("localhost");
        return redis.get(theToken).equals(userName);
    }

    public static void saveToken(String theToken, String userName) {
        Jedis redis = new Jedis("localhost");
        redis.set(theToken, userName);
//        expire after 1 week
        redis.expire(theToken, 604800);
    }
}
