package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.service.Database;
import com.codecool.web.service.MockDatabase;
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

        Map<Integer, String> quizList = database.getArticleIds();
        List<Integer> avaiableQuiz = database.getQuizIdsByLevel(currentUser.getProgress());
        List<Integer> committed = currentUser.getFilledTests();
        List<Integer> passed = currentUser.getOkTests();

        req.setAttribute("quizes", quizList);
        req.setAttribute("avaiable", avaiableQuiz);
        req.setAttribute("committed", committed);
        req.setAttribute("passed", passed);

        requestDispatcher.forward(req, resp);
    }
}
