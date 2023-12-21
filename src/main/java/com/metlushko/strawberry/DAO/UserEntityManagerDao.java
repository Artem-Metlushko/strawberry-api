package com.metlushko.strawberry.DAO;


import com.metlushko.strawberry.entity.User;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Optional;


@AllArgsConstructor
public class UserEntityManagerDao {

    private final SessionFactory sessionFactory;

    public void save(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()
        ) {
            transaction = session.beginTransaction();

            session.save(user);

            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }


    }

    public Optional<User> findById(Long id) {
        Transaction transaction = null;
        User user = null;
        try (Session session = sessionFactory.getCurrentSession()
        ) {
            transaction = session.beginTransaction();

            user = session.get(User.class, id);

            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }return Optional.ofNullable(user);
    }


}
