package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegistrationServlet extends AbstractServlet {

    private final UserService service = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("text/html");

            String un = req.getParameter("username");
            String pw = req.getParameter("password");
            String em = req.getParameter("email");
            String role = req.getParameter("role");

            if (!service.validateRegistration(un, em)) {
                if (!service.validateUniqueUsername(un)) {
                    req.setAttribute("error", "Username is already taken!");
                }
                if (!service.validateUniqueEmail(em)) {
                    req.setAttribute("error", "E-mail address is already taken!");
                }
                req.getRequestDispatcher("register.jsp").forward(req, resp);
            } else {
                User user = new User(un, pw, em, role);
                service.addUser(user);
                resp.sendRedirect("index.jsp");
            }
        } catch (SQLException e) {
            handleError(e, req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }
}
