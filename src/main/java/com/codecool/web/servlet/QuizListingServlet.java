package com.codecool.web.servlet;

import com.codecool.web.service.Database;
import com.codecool.web.service.MockDatabase;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/quizlist")
public class QuizListingServlet extends HttpServlet {

    private Database database = MockDatabase.getInstance();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("quizlist.jsp");
        Map<Integer, String> quizList = database.getArticleIds();
        req.setAttribute("quizes", quizList);

        requestDispatcher.forward(req, resp);
    }
}
