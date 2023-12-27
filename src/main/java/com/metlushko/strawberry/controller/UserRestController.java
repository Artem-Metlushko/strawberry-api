package com.metlushko.strawberry.controller;

import com.metlushko.strawberry.entity.User;
import com.metlushko.strawberry.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @GetMapping
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable("id") Long id){
        return userService.findById(id);
    }

    @PostMapping
    public  User save(@RequestBody User user){
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable ("id") long id, @RequestBody User newUser){

        User user = userService.findById(id);
        user.setAddress(newUser.getAddress());
        user.setName(newUser.getName());
        user.setPhoneNumber(newUser.getPhoneNumber());

        userService.update(newUser,id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable long id){
        userService.deleteById(id);
    }









}
