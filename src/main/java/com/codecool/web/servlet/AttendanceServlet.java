package com.codecool.web.servlet;

import com.codecool.web.service.Database;
import com.codecool.web.model.User;
import com.codecool.web.service.MockDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/attendance")
public class AttendanceServlet extends HttpServlet {

    private Database database = MockDatabase.getInstance();
    private User[] users = database.getUsersArray();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", users);
        req.getRequestDispatcher("attendance.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dateString = req.getParameter("date");

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = df.parse(dateString);
            for (User u : users) {
                u.setAttendance(date, Boolean.valueOf(req.getParameter(u.getUsername())));
            }
        } catch (ParseException e) {
            req.setAttribute("errorMessage", "Invalid date!");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
