package com.codecool.web.servlet;

import com.codecool.web.model.Article;
import com.codecool.web.service.Database;
import com.codecool.web.service.MockDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add_article")
public class ArticleServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Database database = MockDatabase.getInstance();
        resp.setContentType("text/html");
        String title = req.getParameter("article_title");
        String content = req.getParameter("content");

        Article newArticle = new Article(title, content);
        database.addArticle(newArticle);
        resp.sendRedirect("content");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("add_article.html").forward(req, resp);
    }
}
