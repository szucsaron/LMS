package com.codecool.web.filter;


import com.codecool.web.dao.UserDao;
import com.codecool.web.model.User;
import com.codecool.web.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public final class SessionFilter implements Filter {

    private UserService us;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ServletContext context = request.getServletContext();
        DataSource dataSource = (DataSource) context.getAttribute("dataSource");
        try (UserDao userDao = new UserDao(dataSource.getConnection())) {
            us = new UserService(userDao);
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            String path = ((HttpServletRequest) request).getRequestURI();
            HttpSession session = req.getSession();
            if (path.endsWith(".css") ||
                path.endsWith("login") ||
                path.endsWith("register.jsp") ||
                path.endsWith("register") ||
                path.endsWith(".png") ||
                path.endsWith(".jpg") ||

                path.endsWith("index.html") ||
                path.endsWith("sql_test") ||

                path.endsWith("index.jsp")) {

                chain.doFilter(req, resp);
            } else {
                User user = (User) session.getAttribute("user");
                if (user == null) {
                    resp.sendRedirect("index.jsp");
                } else {
                    chain.doFilter(req, resp);
                }
            }
        } catch (SQLException e) {

        }

    }

    @Override
    public void destroy() {
    }
}

