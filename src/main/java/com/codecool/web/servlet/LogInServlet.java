package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LogInServlet extends HttpServlet {

    private final UserService service = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = service.getUsers();
        req.setAttribute("users", users);

        HttpSession oldSession = req.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }

        HttpSession newSession = req.getSession(true);

        newSession.setMaxInactiveInterval(5*60);

        String name = req.getParameter("username");
        String password = req.getParameter("password");


        resp.addCookie(new Cookie("name", name));
        resp.addCookie(new Cookie("password", password));

        for (User u : users) {
            if (u.getUsername().equals(req.getParameter("username")) && u.getPassword().equals(req.getParameter("password"))) {
                    resp.sendRedirect("greeting.jsp");
                }
        }
        resp.sendRedirect("index.html");
    }
}
