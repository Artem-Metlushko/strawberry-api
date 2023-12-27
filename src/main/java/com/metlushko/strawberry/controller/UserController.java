package com.metlushko.strawberry.controller;

import com.metlushko.strawberry.entity.User;
import com.metlushko.strawberry.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller()
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private static final String USER_LIST_JSP = "/userList";
    private static final String USER_FORM = "/userForm";

    private static final String USERS_LINK = "/users";


    @GetMapping("")
    public String list(Model model) {

        List<User> userList = userService.findAll();
        model.addAttribute("usersList", userList);

        return USER_LIST_JSP;
    }

    @GetMapping("/new")
    public String showUserForm(@ModelAttribute("user") User user) {

        return USER_FORM;
    }

    @GetMapping("/{id}")
    public String findUserById(@PathVariable("id") Long id, Model model) {

        User user = userService.findById(id);

        model.addAttribute("user", user);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return USER_FORM;
    }

    @GetMapping("/user")
    public String findUserId(@RequestParam("id") Long id, Model model) {

        User user = userService.findById(id);
        model.addAttribute("user", user);

        return USER_FORM;
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:" + USERS_LINK;
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model) {

        User user = userService.findById(id);
        model.addAttribute("user", user);

        return USER_FORM;
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") long id) {

        userService.update(user, id);

        return "redirect:" + USERS_LINK;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {

        userService.deleteById(id);
        return "redirect:" + USERS_LINK;
    }

}
