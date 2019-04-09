package com.codecool.web.servlet;

import com.codecool.web.dao.QuizDao;
import com.codecool.web.dao.UserDao;
import com.codecool.web.model.User;
import com.codecool.web.model.quiz.Question;
import com.codecool.web.model.quiz.Quiz;
import com.codecool.web.dao.Database;
import com.codecool.web.dao.MockDatabase;
import com.codecool.web.service.QuizService;
import com.codecool.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/quiz")
public class QuizServlet extends AbstractServlet {
    private final String page = "quiz.jsp";

    private User user;
    private QuizService quizService;
    private UserService userService;

    private Quiz quiz;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (QuizDao quizDao = new QuizDao(getConnection(req.getServletContext()));
             UserDao userDao = new UserDao(getConnection(req.getServletContext()))
        ) {
            userService = new UserService(userDao);
            quizService = new QuizService(userDao, quizDao);
            user = userService.getCurrentUser(req);
            int quizId = Integer.parseInt(req.getParameter("quizId"));
            req.setAttribute("quizId", quizId);
            int questionIndex;
            try {
                questionIndex = Integer.parseInt(req.getParameter("questionIndex"));
            } catch (NumberFormatException e) {
                questionIndex = 0;
            }

            Question question = quizDao.getQuestionByQuizAndIndex(quizId, questionIndex);
            quiz = quizDao.getQuizById(quizId);
            if (quizService.validateQuiz(user, quizId)) { // User validation
                handleQuestion(req, resp, question, questionIndex, quiz.size());
            } else {
                resp.sendRedirect("restricted.jsp");
            }
        } catch (SQLException e) {
            handleError(e, req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void handleQuestion(HttpServletRequest req, HttpServletResponse resp, Question question, int questionIndex, int questionNumber) throws ServletException, IOException, SQLException {
        req.setAttribute("score", user.getScore());
        try {
            int answerId = Integer.parseInt(req.getParameter("answerId"));
            quizService.passAnswer(user.getUsername(), answerId);
            questionIndex++;
            if (!quizService.isOver(user, quiz.getId())) {
                question = quizService.getQuestion(quiz.getId(), questionIndex);
                handleRequest(req, resp, question, questionIndex);
            } else {
                handleQuizEnd(req, resp);
            }
        } catch (NumberFormatException e) {
            handleRequest(req, resp, question, questionIndex);
        }


    }


    private void handleRequest(HttpServletRequest req, HttpServletResponse resp, Question question, int questionIndex) throws ServletException, IOException {

        req.setAttribute("questionIndex", questionIndex);
        req.setAttribute("question", question);
        req.setAttribute("score", user.getScore());
        req.getRequestDispatcher(page).forward(req, resp);

    }

    private void handleQuizEnd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        req.setAttribute("result", true);
        quizService.markQuizForEvaluation(quiz, user);
        req.getRequestDispatcher("quizresult.jsp").forward(req, resp);

    }
}
