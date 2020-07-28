package ru.vsi.weatherbot;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
public class DatabaseOperations {
    private final HibernateUtil sessionFactory;
    @Autowired
    public DatabaseOperations(HibernateUtil sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void addorUpdate(long chatId, String city) {
        System.out.println("Adding or updating in Database");
        Session session = sessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(new User(chatId, city));
        session.getTransaction().commit();
        session.close();
    }

    public String city(long chatId) {
        System.out.println("Searching in Database");
        Session session = sessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        String city = session.load(User.class, chatId).getCity();
        session.getTransaction().commit();
        session.close();
        return city;
    }

    public void printUsers(long chatId){
        Session session = sessionFactory.getSessionFactory().openSession();
        List list = session.createQuery("FROM User").list();
        list.forEach(System.out::println);
        session.close();
    }
}
