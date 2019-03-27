package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private UserService service = new UserService();
    private User actualUser = new User();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("profile.jsp");
        List<Cookie> cookies = Arrays.asList(req.getCookies());
        User[] users = service.getUsers();
        String name = "";

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("name")) {
                name = cookie.getValue();
            }
        }
        for (User u : users) {
            if (u.getUsername().equals(name)) {
                actualUser = u;
            }
        }

        req.setAttribute("user", actualUser);
        requestDispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String un = req.getParameter("username");
        String pw = req.getParameter("password");

        actualUser.setUsername(un);
        actualUser.setPassword(pw);
        resp.sendRedirect("content");
    }
}
