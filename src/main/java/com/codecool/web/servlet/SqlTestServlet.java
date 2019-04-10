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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/sql_test")
public class SqlTestServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (QuizDao quizDao= new QuizDao(getConnection(req.getServletContext()))) {
            Map<Integer, QuizEvaluation> cucc = quizDao.getEvaluationForAllQuizzes("Erzsi");
            StringBuilder msg = new StringBuilder();
            for (Integer key : cucc.keySet()) {
                msg.append(String.format("%d: %s <br>", key, cucc.get(key)));
            }
            req.setAttribute("msg", msg.toString());
            req.getRequestDispatcher("sql_test.jsp").forward(req, resp);


        } catch (SQLException e) {
            handleError(e, req, resp);
        }
    }
}
