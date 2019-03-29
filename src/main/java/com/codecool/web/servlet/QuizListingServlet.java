package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.service.database.Database;
import com.codecool.web.service.database.MockDatabase;
import com.codecool.web.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/quizlist")
public class QuizListingServlet extends HttpServlet {

    private Database database = MockDatabase.getInstance();
    private UserService us = new UserService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("quizlist.jsp");

        User currentUser = us.getCurrentUser(req);

        String role = currentUser.getRole();
        Map<Integer, String> quizList = database.getArticleIds();

        if (role.equals("MENTOR")) {
            Map<String, List<Integer>> toCheck = us.getCommittedTests();

            req.setAttribute("check", toCheck);

        } else if (role.equals("STUDENT")) {
            List<Integer> availableQuiz = database.getQuizIdsByLevel(currentUser.getProgress());
            List<Integer> committed = currentUser.getFilledTests();
            List<Integer> passed = currentUser.getOkTests();

            req.setAttribute("available", availableQuiz);
            req.setAttribute("committed", committed);
            req.setAttribute("passed", passed);
        }

        req.setAttribute("role", role);
        req.setAttribute("quizes", quizList);

        requestDispatcher.forward(req, resp);
    }
}
