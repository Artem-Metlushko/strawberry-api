package com.metlushko.strawberry.servlet;

import com.metlushko.strawberry.model.User;
import com.metlushko.strawberry.service.UserService;

import lombok.AllArgsConstructor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private final UserService userService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*String id = request.getParameter("id");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");


        User user = new User(Long.valueOf(id), name, address, phoneNumber);
        userService.saveUser(user);


        response.sendRedirect(request.getContextPath() + "/userInfo.jsp");*/

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");

        if (!id.isEmpty()) {
            User user = userService.getUser(Long.valueOf(id));
            request.setAttribute("user", user);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/userInfo.jsp");
            requestDispatcher.include(request, response);
        } else if (id.isEmpty()) {
            User build = User.builder()
                    .name(name)
                    .address(address)
                    .phoneNumber(phoneNumber)
                    .build();
            userService.saveUser(build);
            request.getRequestDispatcher("/userInfo.jsp").include(request,response);
        }


    }
}
