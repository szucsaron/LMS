package com.codecool.web.servlet;

import com.codecool.web.model.NoSuchUserException;
import com.codecool.web.model.User;
import com.codecool.web.service.Database;
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

        HttpSession oldSession = req.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }
        HttpSession newSession = req.getSession(true);
        newSession.setMaxInactiveInterval(5*60);

        req.getRequestDispatcher("index.html").forward(req, resp);

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String un = req.getParameter("username");
        String pw = req.getParameter("password");

        try {
            if (service.validateLogIn(un, pw)) {
                resp.addCookie(new Cookie("name", un));
                resp.addCookie(new Cookie("password", pw));
                resp.sendRedirect("content");
            } else {
                resp.sendRedirect("index.html");
            }
        } catch (NoSuchUserException e) {
            resp.sendRedirect("index.html");
        }

    }
}
