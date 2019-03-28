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

@WebServlet("/edit_article")
public class EditArticleServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Database database = MockDatabase.getInstance();
        int articleId = Integer.parseInt(req.getParameter("articleId"));
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        Article article = database.getArticle(articleId);
        article.setTitle(title);
        article.setText(content);
        database.modifyArticle(article);
        resp.sendRedirect("content");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Database database = MockDatabase.getInstance();
        resp.setContentType("text/html");
        int articleId = Integer.parseInt(req.getParameter("articleId"));
        Article article = database.getArticle(articleId);
        String title = article.getTitle();
        String content = article.getText();
        req.setAttribute("title", title);
        req.setAttribute("content", content);
        req.setAttribute("articleId", Integer.toString(articleId));


        Article newArticle = new Article(title, content);
        // database.addArticle(newArticle);
        req.getRequestDispatcher("edit_article.jsp").forward(req, resp);

    }

}
