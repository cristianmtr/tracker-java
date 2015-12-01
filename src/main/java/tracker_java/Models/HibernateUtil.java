package tracker_java.Models;

import org.aopalliance.reflect.Class;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration()
                    .setProperty("hibernate.connection.username", System.getenv("PSQLUSER"))
                    .setProperty("hibernate.connection.password", System.getenv("PSQLPASSWORD"))
                    .configure()
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static List getListFromQuery(String query){
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        Query q = s.createQuery(query);
        List response = q.list();
        s.flush();
        tx.commit();
        s.close();
        try {
            return response;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static Object getOneItemFromQuery(String query){
        return getListFromQuery(query).get(0);
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
