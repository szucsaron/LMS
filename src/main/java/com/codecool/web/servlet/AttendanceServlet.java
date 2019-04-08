package com.codecool.web.servlet;

import com.codecool.web.dao.Database;
import com.codecool.web.model.User;
import com.codecool.web.dao.MockDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
        try {
            Database database = MockDatabase.getInstance();
            users = database.getUsersArray();
            req.setAttribute("users", users);
            req.getRequestDispatcher("attendance.jsp").forward(req, resp);
        } catch (SQLException e){
            handleError(e, req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dateString = req.getParameter("date");

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

        try {
            Date date = df.parse(dateString);
            for (User u : users) {
                u.setAttendance(date, Boolean.valueOf(req.getParameter(u.getUsername())));
            }
        } catch (ParseException e) {
            req.setAttribute("errorMessage", "Invalid date!");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        resp.sendRedirect("content");
    }
}
