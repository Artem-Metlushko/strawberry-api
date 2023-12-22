package com.metlushko.strawberry.servlet;

import com.metlushko.strawberry.DAO.UserDAO;
import com.metlushko.strawberry.model.User;
import com.metlushko.strawberry.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


@AllArgsConstructor
@WebServlet(urlPatterns = {"/api/*"})
public class Controller extends HttpServlet {

    private final UserDAO userDAO = new UserDAO(new HashMap<>(), new Random());
    private final UserService userService = new UserService(userDAO);
    private static final String USER_FORM_JSP = "/WEB-INF/userForm.jsp";
    private static final String USER_LIST_JSP = "/WEB-INF/userList.jsp";
    public static final String LIST_USERS = "/api/list";

    @Override
    public void init() {

        userDAO.save(User.builder()
                .name("vaca")
                .phoneNumber("3487952")
                .address("Gomel")
                .build());

        userDAO.save(User.builder()
                .name("peter")
                .phoneNumber("4875214")
                .address("Minsk")
                .build());

        userDAO.save(User.builder()
                .name("ura")
                .address("China")
                .phoneNumber("123123213")
                .build());

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "/";
        }
        switch (action) {
            case "/insertUser" -> createUser(request, response);
            case "/new" -> form(request, response);
            case "/delete" -> deleteUser(request, response);
            case "/edit" -> showEditForm(request, response);
            case "/updateUser" -> updateUser(request, response);
            default -> listUser(request, response);
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String parameter = request.getParameter("id");
        long id = Long.parseLong(parameter);
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");

        User build = User.builder()
                .userId(id)
                .name(name)
                .address(address)
                .phoneNumber(phoneNumber)
                .build();

        userDAO.update(build, id);
        response.sendRedirect(LIST_USERS);

    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        long l = Long.parseLong(id);
        User getUser = userService.getUser(l);
        request.setAttribute("user", getUser);
        request.getRequestDispatcher(USER_FORM_JSP).forward(request, response);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        long l = Long.parseLong(id);
        userService.deleteUser(l);

        response.sendRedirect(LIST_USERS);
    }

    private void form(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(USER_FORM_JSP).forward(request, response);
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");


        User build = User.builder()
                .name(name)
                .address(address)
                .phoneNumber(phoneNumber)
                .build();

        userService.saveUser(build);
        response.sendRedirect(LIST_USERS);

    }

    private void getUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (!id.isEmpty()) {
            User user = userService.getUser(Long.valueOf(id));
            request.setAttribute("user", user);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(USER_FORM_JSP);
            requestDispatcher.include(request, response);

        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        List<User> userList = userService.getUserList();
        request.setAttribute("usersList", userList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(USER_LIST_JSP);
        requestDispatcher.forward(request, response);
    }
}
