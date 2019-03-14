package com.codecool.web.servlet;

import com.codecool.web.model.Article;
import com.codecool.web.model.User;
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

    private User user;
    private final UserService service = new UserService();

    private Database database = Database.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.getParameter("articleId");
        user = new UserService().getCurrentUser(req);
        int articleId = Integer.parseInt(req.getParameter("articleId")); // Mock
        Article article = database.getArticle(articleId);
        if (article.hasAccess(user)) {
            Quiz quiz = article.getQuiz();
            if (!user.quizStarted()) {
                user.beginQuiz(quiz);
                handleQuiz(articleId, req, resp, quiz);
            } else if (user.validateQuiz(quiz)) {
                handleQuiz(articleId, req, resp, quiz);
            }
        } else {
            resp.sendRedirect("restricted.jsp");
        }

    }
        private void handleQuiz(int articleId, HttpServletRequest req, HttpServletResponse resp, Quiz quiz) throws ServletException, IOException{
            req.setAttribute("score", user.getScore());

            try {
                int questionId = Integer.parseInt(req.getParameter("questionId"));
                int answerId = Integer.parseInt(req.getParameter("answerId"));
                QuizManager quizManager = new QuizManager(user, quiz);
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
            req.setAttribute("score", user.getScore());
            req.getRequestDispatcher(page).forward(req, resp);

    }

    private void handleQuizEnd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("result", true);
        user.endQuiz(1);
        req.getRequestDispatcher("quizresult.jsp").forward(req, resp);

    }
}
