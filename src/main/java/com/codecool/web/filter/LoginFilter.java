package com.codecool.web.filter;

import com.codecool.web.model.NoSuchUserException;
import com.codecool.web.model.User;
import com.codecool.web.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebFilter("/protected/*")
public final class LoginFilter implements Filter {

    private final UserService us = new UserService();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        try {
            if (us.validateLogIn(req.getParameter("username"), req.getParameter("username"))) {
                chain.doFilter(req, resp);
            } else {
                req.setAttribute("errorMsg", "Wrong username or password.");
                resp.sendRedirect("../index.html");
            }
        } catch (NoSuchUserException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
    }
}
