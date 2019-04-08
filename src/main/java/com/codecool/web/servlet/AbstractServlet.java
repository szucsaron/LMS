package com.codecool.web.servlet;

import com.codecool.web.dao.Database;
import com.codecool.web.dao.DatabaseImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

abstract class AbstractServlet extends HttpServlet {

    Connection getConnection(ServletContext sce) throws SQLException {
        DataSource dataSource = (DataSource) sce.getAttribute("dataSource");
        return dataSource.getConnection();
    }

    public Database getDatabase() throws SQLException{
        return new DatabaseImpl(getConnection(getServletContext()));
    }

    protected void handleError(Exception e, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("msg", e.getLocalizedMessage());
        req.getRequestDispatcher("error.jsp").forward(req, resp);
    }
}
