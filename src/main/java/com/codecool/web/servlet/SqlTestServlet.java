package com.codecool.web.servlet;

import com.codecool.web.model.Article;
import com.codecool.web.dao.DatabaseImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/sql_test")
public class SqlTestServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String err = "";
        try {
            DatabaseImpl db = new DatabaseImpl(getConnection(req.getServletContext()));
            Article article = db.getArticle(0);
            List<String> titles = db.doStuff();
            req.setAttribute("titles", titles);

            req.setAttribute("article", article);

        } catch (SQLException e) {
            err = e.getLocalizedMessage();
        }
        req.setAttribute("err", err);
        req.getRequestDispatcher("sql_test.jsp").forward(req, resp);
    }
}
