package com.codecool.web.servlet;

import com.codecool.web.model.Article;
import com.codecool.web.model.Content;
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
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(page);
        int id;
        try {
            id = Integer.parseInt(req.getParameter("pageID"));
        } catch (NumberFormatException e) {
            id = 0;
        }

        Database database = Database.getInstance();
        Article article = database.getArticle(id);

        Map<Integer, String> sidebar = database.getArticleIds();


        req.setAttribute("article", article);
        req.setAttribute("sidebar", sidebar);

        requestDispatcher.forward(req, resp);
    }
}
