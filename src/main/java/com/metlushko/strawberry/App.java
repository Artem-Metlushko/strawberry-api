package com.metlushko.strawberry;

import com.metlushko.strawberry.DAO.UserEntityManagerDao;
import com.metlushko.strawberry.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Optional;


public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(User.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        UserEntityManagerDao managerDao = new UserEntityManagerDao(sessionFactory);


        try {
            Optional<User> byId = managerDao.findById(1L);
            System.out.println(byId);
            managerDao.save(new User("save", "Ivanov", "Ivanov"));
            managerDao.save(new User("save", "Ivanov", "Ivanov"));
            managerDao.save(new User("save", "Ivanov", "Ivanov"));
            managerDao.save(new User("save", "Ivanov", "Ivanov"));
        } finally {
            if (sessionFactory != null && !sessionFactory.isClosed()) {
                sessionFactory.close();
            }
        }

    }
}
