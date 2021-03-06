package com.codecool.web.servlet;

import com.codecool.web.dao.*;
import com.codecool.web.model.Article;
import com.codecool.web.model.User;
import com.codecool.web.model.quiz.QuizEvaluation;
import com.codecool.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/content")
public class ContentServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (UserDao userDao = new UserDao(getConnection(req.getServletContext()))) {
            MockDatabase.getInstance().setLocation(req.getServletContext().getRealPath("/"));
            User user = new UserService(userDao).getCurrentUser(req);
            try (ArticleDao articleDao = new ArticleDao(getConnection(req.getServletContext()))) {
                showContent(user, req, resp, articleDao);
            } catch (SQLException e) {
                handleError(e, req, resp);
            }
        } catch (SQLException e) {
            handleError(e, req, resp);
        }

    }

    private void showContent(User user, HttpServletRequest req, HttpServletResponse resp, ArticleDao articleDao) throws ServletException, IOException, SQLException {
        int id;

        try {
            id = Integer.parseInt(req.getParameter("pageID"));
        } catch (NumberFormatException e) {
            id = 1;
        }

        Article article = articleDao.getArticle(id);
        if (article.hasAccess(user)) {
            req.setAttribute("articleId", id);
            req.setAttribute("article", article);
        } else {
            req.setAttribute("articleId", id);
            req.setAttribute("article", new Article("Restricted material", "Your progress is too low to view this article. Please, practice more \n" +
                "or have a bigger wallet."));
        }

        Map<Integer, String> sidebar;

        String toFind = req.getParameter("search");
        if (toFind == null) {
            sidebar = articleDao.getArticleIds();
        } else {
            sidebar = articleDao.getArticleIdsBySearch(toFind);
        }

        req.setAttribute("sidebar", sidebar);

        if (user.getRole().toUpperCase().equals("MENTOR")) {
            req.getRequestDispatcher("mentorContent.jsp").forward(req, resp);
        } else if (user.getRole().toUpperCase().equals("STUDENT")) {
            handleStudent(req, resp, user, article.getQuizId());
        }
    }

    private void handleStudent(HttpServletRequest req, HttpServletResponse resp, User user, int quizId) throws ServletException, IOException, SQLException {
        try (QuizDao quizDao = new QuizDao(getConnection(req.getServletContext()))) {
            QuizEvaluation qe = quizDao.getQuizEvaluation(user.getUsername(), quizId);

            req.setAttribute("quizEval", qe);
            req.getRequestDispatcher("studentContent.jsp").forward(req, resp);
        }
    }
}
