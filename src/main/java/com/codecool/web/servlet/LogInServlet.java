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
        req.getRequestDispatcher("index.html").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = service.getUsers();
        req.setAttribute("users", users);
        resp.setContentType("text/html");

        String un = req.getParameter("username");
        String pw = req.getParameter("password");

        HttpSession oldSession = req.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }
        HttpSession newSession = req.getSession(true);
        newSession.setMaxInactiveInterval(5*60);

        resp.addCookie(new Cookie("name", un));
        resp.addCookie(new Cookie("password", pw));

        if (service.validateLogIn(un, pw)) {
            resp.sendRedirect("content.jsp");
        }
        resp.sendRedirect("index.html");
    }
}
