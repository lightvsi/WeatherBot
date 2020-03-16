import junit.framework.TestCase;
import org.hibernate.Session;
import java.util.ArrayList;
import java.util.List;

public class HibernateTest extends TestCase {

    public void testHibernate() {
        System.out.println("Maven + Hibernate + MySQL");
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        ArrayList<User> users = new ArrayList<>();
        /*users.add(new User(2058L, "moscow"));
        users.add(new User(3082L, "london"));
        users.forEach(x ->  session.saveOrUpdate(x));*/
        System.out.println(session.contains(new User(3082L)));
        List list = session.createQuery("FROM User").list();
        list.forEach(System.out::println);
        System.out.println(session.load(User.class, 2058L).getCity());
        session.getTransaction().commit();
        session.close();
    }
}
