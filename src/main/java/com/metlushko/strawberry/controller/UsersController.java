package com.metlushko.strawberry.controller;

import com.metlushko.strawberry.entity.User;
import com.metlushko.strawberry.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller()
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    private final List<User> users = new ArrayList<>();


    private static final String LIST_USERS = "/api/list";

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String list(Model model) {


        List<User> userList = userService.findAll();
        model.addAttribute("usersList", userList);
        return "/userList";

    }

    @GetMapping("/new")
    public String showUserForm(@ModelAttribute("user") User user) {

        return "/userForm";
    }

    //  http://localhost:8081/users/11
    @GetMapping("/{id}")
    public String findUserById(
            @PathVariable("id") Long id, Model model
    ) {

        User user = userService.findById(id);
        model.addAttribute("user", user);

        return "/userForm";
    }

    // http://localhost:8081/users/user?id=11
    @GetMapping("/user")
    public String findUserId(
            @RequestParam("id") Long id, Model model
    ) {

        User user = userService.findById(id);
        model.addAttribute("user", user);

        return "/userForm";

    }

/*    @GetMapping("/{id}")
    public String findUserById(HttpServletRequest request, Model model) {

        long id = Long.parseLong(request.getParameter("id"));

        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "/userForm";

    }*/



    @PostMapping
    public String create(@RequestParam("name") String name,
                         @RequestParam("address") String address,
                         @RequestParam ("phoneNumber") String phoneNumber){

        User user = new User(name, address, phoneNumber);

        userService.save(user);

        return "redirect:/users";
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
