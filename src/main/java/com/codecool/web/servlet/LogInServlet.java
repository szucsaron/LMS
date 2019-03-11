package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LogInServlet extends HttpServlet {

    private final UserService service = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = service.getUsers();

        for (User u : users) {
            if (u.getUsername().equals(req.getParameter("username"))) {
                if (!u.getPassword().equals(req.getParameter("password"))) {
                    System.out.println("Wrong password!");
                    return;
                }
            }
        }
        req.getRequestDispatcher("whatever.jsp");
    }
}
