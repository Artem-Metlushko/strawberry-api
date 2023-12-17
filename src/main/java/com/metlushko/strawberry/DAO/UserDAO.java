package com.metlushko.strawberry.DAO;

import com.metlushko.strawberry.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;


@Component
public class UserDAO {
    private static Map<String, User> userMap = new HashMap<>();

    static {
        userMap.put("100", User.builder()
                .userId(100L)
                .name("vaca")
                .address("qweqw")
                .phoneNumber("123123213")
                .address("aaaaaa")
                .build());
        userMap.put("101", User.builder()
                .userId(101L)
                .name("peter")
                .address("fdfdf")
                .phoneNumber("123123213")
                .address("aaaaaa")
                .build());
        userMap.put("102", User.builder()
                .userId(102L)
                .name("ura")
                .address("zzzzzz")
                .phoneNumber("123123213")
                .address("aaaaaa")
                .build());
        userMap.put("103", User.builder()
                .userId(103L)
                .name("Artme")
                .address("adasdas")
                .phoneNumber("123123213")
                .address("aaaaaa")
                .build());
    }


    public User getUser(long id) {
        User user = userMap.get(String.valueOf(id));
        return user;
    }

    public List<User> getUserList() {
        return new ArrayList<>(userMap.values());
    }

    public User save(User user) {
        long l = new Random().nextLong()* 1000L;
        user.setUserId(l);
        String key = String.valueOf(user.getUserId());
        userMap.put(key,user);

        return userMap.get(key);
    }
}
