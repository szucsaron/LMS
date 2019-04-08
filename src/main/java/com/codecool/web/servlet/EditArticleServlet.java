package com.codecool.web.servlet;

import com.codecool.web.model.Article;
import com.codecool.web.service.database.Database;
import com.codecool.web.service.database.MockDatabase;

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
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String articleIdParam = req.getParameter("articleId");
        if (articleIdParam.equals("new")) {
            Article article = new Article(title, content);
            database.addArticle(article);
        } else {
            int articleId = Integer.parseInt(articleIdParam);
            Article article = database.getArticle(articleId);
            article.setTitle(title);
            article.setText(content);
            database.modifyArticle(article);
        }
        resp.sendRedirect("content");


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Database database = MockDatabase.getInstance();
        resp.setContentType("text/html");
        String articleIdParam = req.getParameter("articleId");
        String title;
        String content;
        if (articleIdParam.equals("new")) {
            title = "";
            content = "";
        } else {
            int articleId = Integer.parseInt(req.getParameter("articleId"));
            Article article = database.getArticle(articleId);
            title = article.getTitle();
            content = article.getText();
        }
        req.setAttribute("title", title);
        req.setAttribute("content", content);
        req.setAttribute("articleId", articleIdParam);


        req.getRequestDispatcher("edit_article.jsp").forward(req, resp);

    }

}
