package com.codecool.web.servlet;

import com.codecool.web.dao.UserDao;
import com.codecool.web.model.User;
import com.codecool.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/attendance")
public class AttendanceServlet extends AbstractServlet {


    private User[] users;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (UserDao userDao = new UserDao(getConnection(req.getServletContext()))) {
            UserService us = new UserService(userDao);
            Cookie[] cookies = req.getCookies();
            users = us.getUsers();
            DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            Date chosen = formatter.parse(formatter.format(new Date()));
            String chosenString = "";
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("picked")) {
                    chosen = formatter.parse(cookie.getValue());
                    chosenString = cookie.getValue();
                }
            }
            for (User u : users) {
                u.setAttendance(chosen, us.hasAttended(u, chosen));
            }
            req.setAttribute("chosenString", chosenString);
            req.setAttribute("chosen", chosen);
            req.setAttribute("users", users);
            req.getRequestDispatcher("attendance.jsp").forward(req, resp);
        } catch (SQLException e){
            handleError(e, req, resp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (UserDao userDao = new UserDao(getConnection(req.getServletContext()))) {
            UserService us = new UserService(userDao);
            String dateString = req.getParameter("date");
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            try {
                Date date = df.parse(dateString);
                for (User u : users) {
                    String name = u.getUsername();
                    boolean attendance = Boolean.valueOf(req.getParameter(name));
                    us.setAttendance(u, date, attendance);
                }
            } catch (ParseException e) {
                req.setAttribute("error", "Invalid date!");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
            resp.sendRedirect("content");
        } catch (SQLException e) {
            handleError(e, req, resp);
        }
    }
}
