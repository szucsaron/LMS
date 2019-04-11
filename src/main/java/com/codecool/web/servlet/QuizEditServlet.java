package com.codecool.web.servlet;

import com.codecool.web.dao.QuizDao;
import com.codecool.web.dao.UserDao;
import com.codecool.web.model.User;
import com.codecool.web.model.quiz.Answer;
import com.codecool.web.model.quiz.Question;
import com.codecool.web.service.QuizService;
import com.codecool.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/quiz_edit")
public class QuizEditServlet extends AbstractServlet {
    private User user;
    private UserService userServce;
    private QuizService quizService;
    private HttpServletRequest req;
    private HttpServletResponse resp;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.req = req;
        this.resp = resp;
        handle();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.req = req;
        this.resp = resp;
        handle();
    }

    private void handle() throws ServletException, IOException {
        try (QuizDao quizDao = new QuizDao(getConnection(req.getServletContext()));
             UserDao userDao = new UserDao(getConnection(req.getServletContext()))
        ) {
            userServce = new UserService(userDao);
            quizService = new QuizService(userDao, quizDao);
            user = userServce.getCurrentUser(req);
            if (user.getRole() == "MENTOR") {
                provideAccess();
                req.getRequestDispatcher("quiz_edit.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("restricted.jsp");
            }
        } catch (SQLException e) {
            handleError(e, req, resp);
        }
    }

    private void provideAccess() throws ServletException, IOException, SQLException {
        int quizId = Integer.parseInt(req.getParameter("quizId"));
        int questionIndex = getQuestionIndex(req);
        String submit = req.getParameter("submit");
        int prevQuestionIndex = questionIndex;
        if (submit != null) {
            switch (submit) {
                case "NEXT":
                    questionIndex++;
                    break;
                case "PREVIOUS":
                    questionIndex--;
                    break;
                case "SAVE":
                    editQuestion(quizId, questionIndex);
                    break;
                case "NEW QUESTION":
                    quizService.createEmptyQuestion(quizId);
                    break;
            }
        }
        Question question = quizService.getQuestion(quizId, questionIndex);
        if (question == null) {
            questionIndex = prevQuestionIndex;
            question = quizService.getQuestion(quizId, questionIndex);
        }
        req.setAttribute("question", question);
        req.setAttribute("questionIndex", questionIndex);
    }

    private int getQuestionIndex(HttpServletRequest req) {
        try {
            int questionIndex = Integer.parseInt(req.getParameter("questionIndex"));
            return questionIndex;
        } catch (NullPointerException | NumberFormatException e) {
            return 0;
        }
    }

    private void editQuestion(int quizId, int questionIndex) throws SQLException {
        String questionText = req.getParameter("questionText");
        Question question = quizService.getQuestion(quizId, questionIndex);
        question.setDescription(questionText);
        for (Answer answer : question) {
            int id = answer.getId();
            String text = req.getParameter(String.format("ans%d", id));
            answer.setText(text);
        }
        quizService.modifyQuestion(question);
    }
}