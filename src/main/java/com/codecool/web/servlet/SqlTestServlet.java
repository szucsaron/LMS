package com.codecool.web.servlet;

import com.codecool.web.dao.QuizDao;
import com.codecool.web.dao.UserDao;
import com.codecool.web.model.User;
import com.codecool.web.model.quiz.Question;
import com.codecool.web.model.quiz.Quiz;
import com.codecool.web.model.quiz.QuizEvaluation;
import com.codecool.web.model.quiz.Solution;
import com.codecool.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/sql_test")
public class SqlTestServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (UserDao userDao = new UserDao(getConnection(req.getServletContext()))) {
            User user = new User("Jancsi", "anyádNemFilozófus", "hehe@codecool.com", "STUDENT");
            user.setProgress(5);
            UserService userService = new UserService(userDao);
            if (userService.modifyUser(user)) {
                req.setAttribute("msg", user.toString());
            } else {
                req.setAttribute("msg", "A manóba!");
            }

            req.getRequestDispatcher("sql_test.jsp").forward(req, resp);

        } catch (SQLException e) {
            handleError(e, req, resp);
        }
    }
}
