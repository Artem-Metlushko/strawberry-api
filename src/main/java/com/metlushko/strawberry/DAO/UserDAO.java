package com.metlushko.strawberry.DAO;

import com.metlushko.strawberry.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;


@Component
@AllArgsConstructor
public class UserDAO {
    private final Map<String, User> userMap;
    private final Random random;

    public User getUser(long id) {

        return userMap.get(String.valueOf(id));
    }

    public List<User> getUserList() {

        return new ArrayList<>(userMap.values());
    }

    public User save(User user) {
        long l = random.nextInt(1000);
        user.setUserId(l);
        String key = String.valueOf(user.getUserId());
        userMap.put(key, user);

        return userMap.get(key);
    }

    public boolean delete(Long userId) {
        String key = String.valueOf(userId);
        User user = userMap.get(key);

        return userMap.remove(key, user);
    }

    public User update(User userForUpdate, Long id) {
        String key = String.valueOf(id);
        userMap.remove(key);

        return userMap.put(key, userForUpdate);
    }
}
