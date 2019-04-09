package com.codecool.web.servlet;

import com.codecool.web.dao.QuizDao;
import com.codecool.web.model.quiz.Question;
import com.codecool.web.model.quiz.QuizEvaluation;
import com.codecool.web.model.quiz.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/sql_test")
public class SqlTestServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (QuizDao quizDao= new QuizDao(getConnection(req.getServletContext()))) {
            Solution solution = quizDao.getSolution("Jancsi", 0);
            StringBuilder sb = new StringBuilder();
            for (Question question : solution) {
                sb.append(question).append("<br>");
            }
            QuizEvaluation fasz = quizDao.evaluateUserByQuiz("Erzsi", 0);
            QuizEvaluation fasz1 = quizDao.evaluateUserByQuiz("Erzsi", 3);
            QuizEvaluation fasz2 = quizDao.evaluateUserByQuiz("Jancsi", 7);


            req.setAttribute("msg", String.format("%s %s %s", fasz.toString(), fasz1.toString(), fasz2.toString()));
            req.getRequestDispatcher("sql_test.jsp").forward(req, resp);


        } catch (SQLException e) {
            handleError(e, req, resp);
        }
    }
}
