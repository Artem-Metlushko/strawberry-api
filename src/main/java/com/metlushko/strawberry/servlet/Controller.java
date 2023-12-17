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
import java.sql.SQLException;
import java.util.List;


@AllArgsConstructor
@WebServlet(urlPatterns = {"/api/*"})
public class Controller extends HttpServlet {
    private final UserService userService;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();

        try {
            switch (action) {
                case "/insertUser" -> createUser(request, response);
                case "/get" -> getUser(request, response);
                case "/list" -> listUser(request, response);
                case "/new" -> form(request, response);

                case "/delete" -> deleteUser(request, response);
//                case "/edit" -> showEditForm(request, response);
//                case "/update" -> updateUser(request, response);
                default -> listUser(request, response);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        long l = Long.parseLong(id);
        userService.deleteUser(l);
        response.sendRedirect("/api/list");
    }

    private void form(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/userForm.jsp");
        requestDispatcher.forward(request,response);
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");

        if (id.isEmpty()) {
            User build = User.builder()
                    .name(name)
                    .address(address)
                    .phoneNumber(phoneNumber)
                    .build();

            User user = userService.saveUser(build);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/userInfo.jsp").include(request,response);
        }
    }

    private void getUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (!id.isEmpty()) {
            User user = userService.getUser(Long.valueOf(id));
            request.setAttribute("user", user);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/userInfo.jsp");
            requestDispatcher.include(request, response);

        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        List<User> userList = userService.getUserList();
        request.setAttribute("usersList", userList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/userList.jsp");
        requestDispatcher.forward(request, response);
    }
}
