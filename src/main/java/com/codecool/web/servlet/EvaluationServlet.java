package com.codecool.web.servlet;

import com.codecool.web.dao.QuizDao;
import com.codecool.web.model.User;
import com.codecool.web.model.quiz.Solution;
import com.codecool.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/evaluate")
public class EvaluationServlet extends AbstractServlet {

    UserService service = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (QuizDao quizDao = new QuizDao(getConnection(req.getServletContext()))) {
            User user = (User) req.getAttribute("student");
            String userName = req.getParameter("student");

            int quizID = (int) req.getAttribute("id");
            Solution solution = quizDao.getSolution(user.getEmail(), quizID);

            req.setAttribute("solution", solution);

            req.getRequestDispatcher("evaluate.jsp").forward(req, resp);

        } catch (SQLException se) {
            req.setAttribute("error", "SQL Error occured!");
            req.getRequestDispatcher("content.jsp").forward(req, resp);
        }

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
