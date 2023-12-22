package com.metlushko.strawberry;

import com.metlushko.strawberry.DAO.UserEntityManagerDao;
import com.metlushko.strawberry.config.HibernateUtil;
import com.metlushko.strawberry.entity.User;
import org.hibernate.SessionFactory;

import java.util.Optional;


public class App {
    public static void main(String[] args) {


        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        UserEntityManagerDao managerDao = new UserEntityManagerDao(sessionFactory);


        try {

            managerDao.save(new User("save", "Ivanov1", "Ivanov"));
            managerDao.save(new User("save", "Ivanov2", "Ivanov"));
            managerDao.save(new User("save", "Ivanov3", "Ivanov"));
            managerDao.save(new User("save", "Ivanov4", "Ivanov"));
            Optional<User> user1 = managerDao.findById(1L);

            Optional<User> user2 = managerDao.findById(2L);
            Optional<User> user3 = managerDao.findById(3L);
            Optional<User> user4 = managerDao.findById(4L);
            System.out.println(user1);
            System.out.println(user2);
            System.out.println(user3);
            System.out.println(user4);


            managerDao.update(new User("update","Petrov1","Petr"),4L);
            managerDao.delete(3L);
            System.out.println();
        } finally {
            if (sessionFactory != null && !sessionFactory.isClosed()) {
                sessionFactory.close();
            }
        }

    }
}
