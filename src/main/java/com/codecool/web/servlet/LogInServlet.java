package com.codecool.web.servlet;

import com.codecool.web.dao.UserDao;
import com.codecool.web.model.NoSuchUserException;
import com.codecool.web.model.User;
import com.codecool.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LogInServlet extends AbstractServlet {

    private UserService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (UserDao userDao = new UserDao(getConnection(req.getServletContext()))) {
            service = new UserService(userDao);
            HttpSession oldSession = req.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }
            HttpSession newSession = req.getSession(true);
            newSession.setMaxInactiveInterval(5 * 60);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } catch (SQLException e) {
            handleError(e, req, resp);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (UserDao userDao = new UserDao(getConnection(req.getServletContext()))) {
            service = new UserService(userDao);
            resp.setContentType("text/html");

            String un = req.getParameter("username");
            String pw = req.getParameter("password");

            try {
                service.validateLogIn(un, pw);
                User user = service.getCurrentUser(req);
                req.getSession().setAttribute("user", user);
                resp.addCookie(new Cookie("name", un));
                resp.addCookie(new Cookie("password", pw));
                resp.sendRedirect("content");
            } catch (NoSuchUserException e) {
                req.setAttribute("error", "Wrong username or password!");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            handleError(e, req, resp);
        }

    }
}
