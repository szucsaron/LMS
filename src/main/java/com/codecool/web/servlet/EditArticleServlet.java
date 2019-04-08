package com.codecool.web.servlet;

import com.codecool.web.dao.ArticleDao;
import com.codecool.web.model.Article;
import com.codecool.web.dao.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/edit_article")
public class EditArticleServlet extends AbstractServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (ArticleDao articleDao = new ArticleDao(getConnection(req.getServletContext()))) {
            String title = req.getParameter("title");
            String content = req.getParameter("content");
            String articleIdParam = req.getParameter("articleId");
            int level = Integer.parseInt(req.getParameter("level"));
            if (articleIdParam.equals("new")) {
                Article article = new Article(title, content);
                article.setLevel(level);
                articleDao.addArticle(article);
            } else {
                int articleId = Integer.parseInt(articleIdParam);
                Article article = articleDao.getArticle(articleId);
                article.setLevel(level);
                article.setTitle(title);
                article.setText(content);
                articleDao.modifyArticle(article);
            }
            resp.sendRedirect("content");
        } catch (SQLException e) {
            handleError(e, req, resp);
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (ArticleDao articleDao = new ArticleDao(getConnection(req.getServletContext()))) {
            resp.setContentType("text/html");
            String articleIdParam = req.getParameter("articleId");
            String title;
            String content;
            int level;

            if (articleIdParam.equals("new")) {
                title = "";
                content = "";
                level = 1;
            } else {
                int articleId = Integer.parseInt(req.getParameter("articleId"));
                Article article = articleDao.getArticle(articleId);
                title = article.getTitle();
                content = article.getText();
                level = article.getLevel();
            }
            req.setAttribute("title", title);
            req.setAttribute("content", content);
            req.setAttribute("articleId", articleIdParam);
            req.setAttribute("level", level);
            req.getRequestDispatcher("edit_article.jsp").forward(req, resp);
        } catch (SQLException e) {
            handleError(e, req, resp);
        }

    }

}
