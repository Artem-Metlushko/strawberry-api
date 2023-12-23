package com.metlushko.strawberry.DAO;

import com.metlushko.strawberry.entity.User;

import java.util.Optional;

public interface Dao {

    User save(User user);

    void deleteById(Long id);

    void update(User user, Long id);

    Optional<User> findById(Long id);
}
