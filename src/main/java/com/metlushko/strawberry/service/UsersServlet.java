package com.metlushko.strawberry.service;

import com.metlushko.strawberry.model.User;
import lombok.AllArgsConstructor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@AllArgsConstructor
@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private final UserService userService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");


        List<User> userList = userService.getUserList();
        request.setAttribute("usersList", userList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/userList.jsp");
        requestDispatcher.include(request, response);
    }
}
