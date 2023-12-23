package com.metlushko.strawberry;

import com.metlushko.strawberry.DAO.UserEntityManagerDao;
import com.metlushko.strawberry.config.SpringConfig;
import com.metlushko.strawberry.entity.User;
import com.metlushko.strawberry.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        UserEntityManagerDao dao = context.getBean(UserEntityManagerDao.class);

        Optional<User> byId = dao.findById(11L);
        System.out.println(byId);

        User user = dao.save(new User("name", "address", "phoneNumber"));
        System.out.println(user);

        dao.update(new User("newName", "newAddress", "newPhoneNumber"), user.getId());

        dao.delete(11L);

    }
}
