package tracker_java;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

/**
 * Created by cristian on 10/4/15.
 */
public class TestHibernate extends TestCase {
    private SessionFactory sf;

    @Override
    protected void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sf = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
            System.out.println("succes!");
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    @Override
    protected void tearDown() throws Exception {
        if ( sf != null ) {
            sf.close();
        }
    }

    @SuppressWarnings({ "unchecked" })
    public void testBasicUsage() {
        Session session = sf.openSession();
        session.beginTransaction();
        List result = session.createQuery( "from TaskObject" ).list();
        for ( TaskObject task : (List<TaskObject>) result ) {
          System.out.format("task: id %d, title = %s %n", task.DT_RowId, task.title);
        }
        session.getTransaction().commit();
        session.close();
    }
}