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

@WebServlet("/evaluate")
public class EvaluationServlet extends AbstractServlet {

    private UserService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (UserDao userDao = new UserDao(getConnection(req.getServletContext()))) {
            service = new UserService(userDao);
            User user = (User) req.getAttribute("user");
            int quizID = (int) req.getAttribute("id");
            //Solution solution = solutionService.getSolution(user.getEmail(), quizID);
        } catch (SQLException e) {
            handleError(e, req, resp);
        }



    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (UserDao userDao = new UserDao(getConnection(req.getServletContext()))) {
            service = new UserService(userDao);
            // Logic goes here
        } catch (SQLException e) {
            handleError(e, req, resp);
        }


    }
}
