package com.codecool.web.servlet;

import com.codecool.web.dao.QuizDao;
import com.codecool.web.dao.UserDao;
import com.codecool.web.model.User;
import com.codecool.web.service.QuizService;
import com.codecool.web.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/quizlist")
public class QuizListingServlet extends AbstractServlet {

    private UserService us;
    private QuizService qs;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (UserDao userDao = new UserDao(getConnection(req.getServletContext()));
             QuizDao quizDao = new QuizDao(getConnection(req.getServletContext()))) {
            us = new UserService(userDao);
            qs = new QuizService(userDao,quizDao);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("quizlist.jsp");

            User currentUser = us.getCurrentUser(req);
            String currentUserName = currentUser.getUsername();
            String role = currentUser.getRole();
            Map<Integer, String> quizList = qs.getQuizNamesWithLvlLimit(currentUser.getProgress());

            if (role.equals("MENTOR")) {
                Map<String, List<Integer>> toCheck = qs.getAllQuizzesWaitingForEval();
                req.setAttribute("check", toCheck);

            } else if (role.equals("STUDENT")) {
                List<Integer> availableQuiz = qs.getAvailableQuizzes(currentUser);
                List<Integer> committed = qs.getQuizzesWaitingForEval(currentUserName);
                List<Integer> passed = qs.getQuizzesPassed(currentUserName);

                req.setAttribute("available", availableQuiz);
                req.setAttribute("committed", committed);
                req.setAttribute("passed", passed);
            }

            req.setAttribute("role", role);
            req.setAttribute("quizzes", quizList);


            requestDispatcher.forward(req, resp);
        } catch (SQLException e) {
            handleError(e, req, resp);
        }
    }
}
