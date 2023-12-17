package com.metlushko.strawberry.service;

import com.metlushko.strawberry.DAO.UserDAO;
import com.metlushko.strawberry.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserDAO userDAO;

    public User getUser(Long userId){
        return userDAO.getUser(userId);

    }

    public List<User> getUserList(){
        return userDAO.getUserList();
    }

    public User saveUser(User user) {
        return userDAO.save(user);
    }

    public boolean deleteUser(Long userId){
        return userDAO.delete(userId);
    }
}
