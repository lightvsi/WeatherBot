import junit.framework.TestCase;
import org.hibernate.Session;

public class HibernateTest extends TestCase {

    public void testHibernate() {
        System.out.println("Maven + Hibernate + MySQL");
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User user = new User(2058L, "london");
        session.saveOrUpdate(user);
        session.getTransaction().commit();
        session.disconnect();
    }
}