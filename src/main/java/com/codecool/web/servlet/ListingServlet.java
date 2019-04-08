package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/users")
public class ListingServlet extends AbstractServlet {

    private UserService service = new UserService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("users.jsp");
            User[] users = service.getUsers();
            req.setAttribute("users", users);

            requestDispatcher.forward(req, resp);
        } catch (SQLException e) {
           handleError(e, req, resp);
        }
    }
}
