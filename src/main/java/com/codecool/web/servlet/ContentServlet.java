package com.codecool.web.servlet;

import com.codecool.web.model.Article;
import com.codecool.web.model.Content;
import com.codecool.web.service.Database;
import com.codecool.web.service.UserService;

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
        Database database = Database.getInstance();
        Article article = database.getArticle(0);

        Map<Integer, String> sidebar = new HashMap<>();
        sidebar.put(1, "White goat");
        sidebar.put(2, "Black goat");
        sidebar.put(3, "Not a goat");


        req.setAttribute("article", article);
        req.setAttribute("sidebar", sidebar);

        req.getRequestDispatcher(page).forward(req, resp);



    }
}
