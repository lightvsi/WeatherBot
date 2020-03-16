import org.hibernate.Session;
import java.util.ArrayList;
import java.util.List;

public class DatabaseOperations {

    public static void addorUpdate(long chatId, String city) {
        System.out.println("addingtoDatabase");
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(new User(chatId, city));
        session.getTransaction().commit();
        session.close();
    }

    public static String city(long chatId) {
        System.out.println("searchinginDatabase");
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String city = session.load(User.class, chatId).getCity();
        session.getTransaction().commit();
        session.close();
        return city;
    }

    public static void printUsers(long chatId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List list = session.createQuery("FROM User").list();
        list.forEach(System.out::println);
    }
}
