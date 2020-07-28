package ru.vsi.weatherbot;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

//import org.hibernate.service.ServiceRegistryBuilder;
@Component
public class HibernateUtil {

    private final SessionFactory sessionFactory;

      public HibernateUtil() {
        try {
            sessionFactory = new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
        }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

   public SessionFactory getSessionFactory() {
       return sessionFactory;
    }

    public void shutdown() {
        sessionFactory.close();
    }

}