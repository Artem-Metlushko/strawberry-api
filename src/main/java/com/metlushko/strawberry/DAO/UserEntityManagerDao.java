/*
package com.metlushko.strawberry.DAO;


import com.metlushko.strawberry.entity.User;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@AllArgsConstructor
@Component
public class UserEntityManagerDao implements Dao {

    private final SessionFactory sessionFactory;
    private Session session;

    @Transactional
    public User save(User user) {
        session = sessionFactory.getCurrentSession();
        session.save(user);
        return user;
    }

    @Transactional
    public Optional<User> findById(Long id) {
        session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        return Optional.ofNullable(user);
    }

    @Transactional
    public void deleteById(Long id) {
        session = sessionFactory.getCurrentSession();
        session.remove(session.get(User.class, id));
    }

    @Transactional
    public void update(User user, Long id) {
        session = sessionFactory.getCurrentSession();
        User userUpdated = session.get(User.class, id);
        userUpdated.setName(user.getName());
        userUpdated.setPhoneNumber(user.getPhoneNumber());

    }

}
*/
