package com.metlushko.strawberry;

import com.metlushko.strawberry.DAO.UserDAO;
import com.metlushko.strawberry.config.SpringConfig;
import com.metlushko.strawberry.model.User;
import com.metlushko.strawberry.service.UserService;
import com.metlushko.strawberry.servlet.Controller;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        Map<String, User> map = context.getBean("map", Map.class);
        map.put("1", new User("Vasya", "123123", "Moscow"));
        System.out.println(map.get("1"));

        Random random = context.getBean("random", Random.class);
        System.out.println(random);

        UserDAO userDAO = context.getBean("userDAO", UserDAO.class);
        System.out.println(userDAO);

        System.out.println(context.getBean("userService", UserService.class).getUserList());

        Controller controller = context.getBean("controller", Controller.class);
        System.out.println(controller);
//        context.close();



    }
}
