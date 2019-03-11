package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/greeting")
public class LogInServlet extends HttpServlet {

    private final UserService service = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        List<User> users = service.getUsers();

        for (User u : users) {
            if (u.getUsername().equals(req.getParameter("username"))) {
                if (!u.getPassword().equals(req.getParameter("password"))) {
                    System.out.println("Wrong password!");
                    return;
                }
            }
        }
        */
        //get the old session and invalidate
        //req.getRequestDispatcher("greeting.jsp").forward(req, resp);
        HttpSession oldSession = req.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }
        //generate a new session
        HttpSession newSession = req.getSession(true);

        //setting session to expiry in 5 mins
        newSession.setMaxInactiveInterval(5*60);

        String name = req.getParameter("username");
        String password = req.getParameter("pwd");


        resp.addCookie(new Cookie("name", name));
        resp.addCookie(new Cookie("password", password));

        resp.sendRedirect("greeting.jsp");


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("greeting.jsp");



        //resp.sendRedirect("greeting.jsp");
    }
}
