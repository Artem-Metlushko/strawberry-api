package com.metlushko.strawberry.controller;

import com.metlushko.strawberry.entity.User;
import com.metlushko.strawberry.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

    private final UserService userService;



    private static final String LIST_USERS = "/api/list";

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/list")
    public String list(Model model) {

        model.addAttribute("userList", userService.findAll());
        return "/userList";

    }

    @GetMapping("/api/{id}")
    public String findUserById(@PathVariable("id") String id, Model model) {
        User getUser = userService.findById(Long.valueOf(id));
        model.addAttribute("user", getUser);
        return "/userForm";
    }
}
