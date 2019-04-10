package com.codecool.web.servlet;

import com.codecool.web.dao.QuizDao;
import com.codecool.web.dao.UserDao;
import com.codecool.web.model.User;
import com.codecool.web.model.quiz.Question;
import com.codecool.web.service.QuizService;
import com.codecool.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/quiz_edit")
public class QuizEditServlet extends AbstractServlet {
    private User user;
    private UserService userServce;
    private QuizService quizService;


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (QuizDao quizDao = new QuizDao(getConnection(req.getServletContext()));
             UserDao userDao = new UserDao(getConnection(req.getServletContext()))
        ) {

            userServce = new UserService(userDao);
            quizService = new QuizService(userDao, quizDao);
            user = userServce.getCurrentUser(req);
            if (user.getRole() == "MENTOR") {
                provideAccess(req, resp);
                req.getRequestDispatcher("quiz_edit.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("restricted.jsp");
            }
        } catch (SQLException e) {
            handleError(e, req, resp);
        }
    }

    protected void provideAccess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        int quizId = Integer.parseInt(req.getParameter("quizId"));
        int questionIndex = Integer.parseInt(req.getParameter("questionIndex"));
        Question question = quizService.getQuestion(quizId, questionIndex);
        req.setAttribute("question", question);

    }


}
