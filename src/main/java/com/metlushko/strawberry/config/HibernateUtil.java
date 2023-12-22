package com.metlushko.strawberry.config;

import com.metlushko.strawberry.entity.User;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil  {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().addAnnotatedClass(User.class).buildSessionFactory();
        }
        return sessionFactory;
    }

}
