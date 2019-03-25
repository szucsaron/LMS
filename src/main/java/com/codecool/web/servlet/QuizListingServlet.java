package com.codecool.web.servlet;

import com.codecool.web.model.Article;
import com.codecool.web.service.Database;

import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/quizlist")
public class QuizListingServlet extends HttpServlet {

    private Database database = new Database();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("quizlist.jsp");

        req.setAttribute("quizes", database.getArticleIds());

        requestDispatcher.forward(req, resp);
    }
}
