package com.metlushko.strawberry.DAO;


import com.metlushko.strawberry.entity.User;
import com.metlushko.strawberry.exception.ResourceNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
public class UserEntityManagerDao {

    private final SessionFactory sessionFactory;

    public UserEntityManagerDao(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public User save(User user) {
        Session session = sessionFactory.getCurrentSession();

        session.save(user);
        Long id = user.getId();
        return session.get(User.class, id);

    }

    @Transactional
    public User findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);

        if(user== null){
            throw  new ResourceNotFoundException();
        }
        return user;
    }

    @Transactional
    public User update(User userUpdate, Long id) {
        Session session = sessionFactory.getCurrentSession();
        User userToUpdate = session.get(User.class, id);

        userToUpdate.setName(userUpdate.getName());
        userToUpdate.setAddress(userUpdate.getAddress());
        userToUpdate.setPhoneNumber(userUpdate.getPhoneNumber());

        return userToUpdate;


    }

    @Transactional
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();

        User userToDelete = session.get(User.class, id);
        session.remove(userToDelete);


    }

    @Transactional
    public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select u from User u", User.class).getResultList();
    }
}
