package com.codecool.web.servlet;

import com.codecool.web.model.quiz.Question;
import com.codecool.web.model.quiz.Quiz;
import com.codecool.web.service.Database;
import com.codecool.web.service.QuizManager;
import com.codecool.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/quiz")
public class QuizServlet extends HttpServlet {
    private final String page = "quiz.jsp";


    private final UserService service = new UserService();

    private Database database = Database.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.getParameter("articleId");
        int articleId = Integer.parseInt(req.getParameter("articleId")); // Mock
        Quiz quiz = database.getArticle(articleId).getQuiz();
        req.setAttribute("score", database.score);

        try {
            int questionId = Integer.parseInt(req.getParameter("questionId"));
            int answerId = Integer.parseInt(req.getParameter("answerId"));
            QuizManager quizManager = new QuizManager(database, quiz);
            quizManager.handleNext(questionId, answerId);
            questionId++;
            if (questionId < quiz.size()) {
                handleRequest(req, resp, quiz, articleId, questionId);
            } else {
                handleQuizEnd(req, resp);
            }
        } catch (NumberFormatException e) {
            handleRequest(req, resp, quiz, articleId, 0);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void handleRequest(HttpServletRequest req, HttpServletResponse resp, Quiz quiz, int articleId, int questionId) throws ServletException, IOException{
            Question question = quiz.getQuestion(questionId);
            req.setAttribute("questionId", questionId);
            req.setAttribute("articleId", articleId);
            req.setAttribute("question", question);
            req.setAttribute("score", database.score);
            req.getRequestDispatcher(page).forward(req, resp);

    }

    private void handleQuizEnd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("result", true);

        req.getRequestDispatcher("quizresult.jsp").forward(req, resp);

    }
}
