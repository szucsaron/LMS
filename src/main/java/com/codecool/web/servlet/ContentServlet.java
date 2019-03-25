package com.codecool.web.servlet;

import com.codecool.web.model.Article;
import com.codecool.web.model.User;
import com.codecool.web.service.Database;
import com.codecool.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;

@WebServlet("/content")
public class ContentServlet extends HttpServlet {

    private final UserService service = new UserService();
    private final String page = "content.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Database.getInstance().setLocation(req.getServletContext().getRealPath("/"));
        User user = new UserService().getCurrentUser(req);
        showContent(user, req, resp);
    }

    private void showContent(User user, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int id;
        try {
            id = Integer.parseInt(req.getParameter("pageID"));
        } catch (NumberFormatException e) {
            id = 0;
        }

        Database database = Database.getInstance();
        Article article = database.getArticle(id);
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
            sidebar = database.getArticleIds();
        } else {
            sidebar = database.getArticleIdsBySearch(toFind);
        }

        req.setAttribute("sidebar", sidebar);

        req.getRequestDispatcher(page).forward(req, resp);
    }
}
