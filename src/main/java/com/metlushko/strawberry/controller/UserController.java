package com.metlushko.strawberry.controller;

import com.metlushko.strawberry.entity.User;
import com.metlushko.strawberry.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller()
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;


    private static final String LIST_USERS = "/api/list";


    @GetMapping("/list")
    public String list(Model model) {

        model.addAttribute("usersList", userService.findAll());
        return "/userList";

    }

    @GetMapping("/new")
    public String showUserForm(Model model) {
        model.addAttribute("user", new User());
        return "/userForm";
    }

    @GetMapping("/api/{id}")
    public String findUserById(@PathVariable("id") Long id, Model model) {

        User user = userService.findById(id);
        model.addAttribute("user", user);

        return "/userForm";

    }


    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") long id) {
        userService.deleteById(id);
        return "redirect:/api/list";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "/userForm";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.update(user, user.getId());
        return "redirect:/api/list";
    }
}
