package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.model.quiz.Question;
import com.codecool.web.model.quiz.Quiz;
import com.codecool.web.dao.Database;
import com.codecool.web.dao.MockDatabase;
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
    private final UserService service = new UserService();

    private Database database = MockDatabase.getInstance();
    private Quiz quiz;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            MockDatabase.getInstance().setLocation(req.getServletContext().getRealPath("/"));
            user = new UserService().getCurrentUser(req);
            int quizId = Integer.parseInt(req.getParameter("quizId"));
            req.setAttribute("quizId", quizId);
            int questionIndex;
            try {
                questionIndex = Integer.parseInt(req.getParameter("questionIndex"));
            } catch (NumberFormatException e) {
                questionIndex = 0;
            }


            Question question = database.getQuestionByQuizAndIndex(quizId, questionIndex);
            quiz = database.getQuizById(quizId);
            if (user.validateQuiz(quiz)) { // User validation
                if (!user.quizStarted()) {
                    user.beginQuiz(quiz);
                }
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

    private void handleQuestion(HttpServletRequest req, HttpServletResponse resp, Question question, int questionIndex, int questionNumber) throws ServletException, IOException {
        req.setAttribute("score", user.getScore());
        try {
            int answerId = Integer.parseInt(req.getParameter("answerId"));
            if (question.validateAnswer(answerId)) {
                user.incrementScore();
            }
            questionIndex++;
            if (questionIndex < questionNumber) {
                question = database.getQuestionByQuizAndIndex(quiz.getId(), questionIndex);
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

    private void handleQuizEnd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("result", true);
        user.endQuiz(1);
        req.getRequestDispatcher("quizresult.jsp").forward(req, resp);

    }
}
