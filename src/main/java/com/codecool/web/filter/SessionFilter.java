package com.codecool.web.filter;

/*
import com.codecool.web.model.User;
import com.codecool.web.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebFilter("/*")
public final class SessionFilter implements Filter {

    private final UserService us = new UserService();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String path = ((HttpServletRequest) request).getRequestURI();
        HttpSession session = req.getSession();
        if (path.endsWith(".css") ||
            path.endsWith("login") ||
            path.endsWith("register.html") ||
            path.endsWith("register") ||
            path.endsWith(".png") ||
            path.endsWith("index.html")) {
            chain.doFilter(req, resp);
        } else {
            User user = (User) session.getAttribute("user");
            if (user == null) {
                resp.sendRedirect("index.html");
            } else {
                chain.doFilter(req, resp);
            }
        }
    }

    @Override
    public void destroy() {
    }
}
*/
