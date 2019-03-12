package com.codecool.web.servlet;

import com.codecool.web.model.Article;
import com.codecool.web.model.Content;
import com.codecool.web.model.User;
import com.codecool.web.service.Database;
import com.codecool.web.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
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

        // Mocking access control
        User user = new User("Józsi", "12345", "lazybastard123@goatmail.com");
        user.setProgress(4);
        int id;
        try {
            id = Integer.parseInt(req.getParameter("pageID"));
        } catch (NumberFormatException e) {
            id = 0;
        }

        Database database = Database.getInstance();
        Article article = database.getArticle(id);;
        if (article.hasAccess(user)) {
            req.setAttribute("article", article);
        } else {
            req.setAttribute("article", new Article("Restricted material", "Your progress is too low to view this article. Please, practice more \n" +
                "or have a bigger wallet."));
        }

        Map<Integer, String> sidebar = database.getArticleIds();


        req.setAttribute("sidebar", sidebar);

<<<<<<< HEAD
        req.getRequestDispatcher(page).forward(req, resp);




=======
        requestDispatcher.forward(req, resp);
>>>>>>> 1bb74b29c72f1ad36e57f3d4114e4b30e5f9bdfb
    }
}
