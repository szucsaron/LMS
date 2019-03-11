package com.codecool.web.servlet;

import com.codecool.web.model.Content;
import com.codecool.web.service.Database;
import com.codecool.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {

    private final UserService service = new UserService();
    private final String page = "index.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Database database = Database.getInstance();
        Content content = database.getContent();
        req.setAttribute("content", content);
        resp.sendRedirect("test.jsp");



    }
}
