package com.metlushko.strawberry.DAO;


import com.metlushko.strawberry.entity.User;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Optional;


@AllArgsConstructor
public class UserEntityManagerDao {

    private final SessionFactory sessionFactory ;

    public void save(User user) {

        try ( Session session = sessionFactory.getCurrentSession()
        ){
            session.beginTransaction();

            session.save(user);

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public Optional<User> findById(Long id) {


        User user;
        try(Session session = sessionFactory.getCurrentSession()
        ) {
            session.beginTransaction();

            user = session.get(User.class, id);

            session.getTransaction().commit();
        }
        return Optional.ofNullable(user);
    }


}
