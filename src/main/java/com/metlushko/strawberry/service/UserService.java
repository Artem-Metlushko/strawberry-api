package com.metlushko.strawberry.service;

import com.metlushko.strawberry.entity.User;

public interface UserService {
    User save(User user);

    User findById(Long id);

    void deleteById(Long id);

    void update(User user, Long id);

}
