package com.metlushko.strawberry.service;

import com.metlushko.strawberry.DAO.UserEntityManagerDao;
import com.metlushko.strawberry.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class UserEntityManagerService implements UserService{
    private final UserEntityManagerDao userEntityManagerDao;


    @Override
    public User save(User user) {
        return userEntityManagerDao.save(user);
    }

    @Override
    public User findById(Long id) {
        return userEntityManagerDao.findById(id)
                .orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        userEntityManagerDao.delete(id);
    }

    @Override
    public void update(User user, Long id) {
        userEntityManagerDao.update(user,id);
    }

    public List<User> findAll(){
        return userEntityManagerDao.findAll();
    }
 }
