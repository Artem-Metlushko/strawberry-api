package com.metlushko.strawberry.config;

import com.metlushko.strawberry.DAO.UserDAO;
import com.metlushko.strawberry.model.User;
import com.metlushko.strawberry.service.UserService;
import com.metlushko.strawberry.servlet.Controller;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@Configuration
@ComponentScan(basePackages = "com.metlushko")
//@EnableWebMvc
public class SpringConfig {


    @Bean
    public Map<String, User> map() {
        return new HashMap<>();
    }

    @Bean
    public Random random() {
        return new Random();
    }

    @Bean
    public UserDAO userDAO() {
        return new UserDAO(map(), random());
    }

    @Bean
    public UserService userService() {
        return new UserService(userDAO());
    }

/*    @Bean
    public Controller controller() {
        return new Controller(userDAO(), userService());
    }*/
}
