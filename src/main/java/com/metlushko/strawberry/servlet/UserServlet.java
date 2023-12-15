package com.metlushko.strawberry.servlet;

import com.metlushko.strawberry.model.User;
import com.metlushko.strawberry.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import java.io.IOException;
@AllArgsConstructor
@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private final UserService userService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");


        User user = userService.getUser(Long.valueOf(id));
        request.setAttribute("user",user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/userInfo.jsp");
        requestDispatcher.include(request,response);
    }
}
