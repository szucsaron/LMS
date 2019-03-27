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

@WebServlet("/attendance")
public class AttendanceServlet extends HttpServlet {
    
    private Database database = MockDatabase.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User[] users = database.getUsersArray();
        req.setAttribute("users", users);
        req.getRequestDispatcher("attendance.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        


    }
}
