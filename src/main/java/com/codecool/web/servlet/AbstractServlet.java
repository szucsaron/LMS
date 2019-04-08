package com.codecool.web.servlet;

import com.codecool.web.service.database.Database;
import com.codecool.web.service.database.DatabaseImpl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;
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
}
