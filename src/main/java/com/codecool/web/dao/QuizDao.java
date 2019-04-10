package com.codecool.web.dao;

import com.codecool.web.model.User;
import com.codecool.web.model.quiz.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizDao extends AbstractDao {
    public QuizDao(Connection connection) {
        super(connection);
    }

    public Question getQuestionByQuizAndIndex(int quizId, int index) throws SQLException {
        String sql = "SELECT id, title FROM questions WHERE quiz_id = ? ORDER BY questions.id LIMIT 1 OFFSET ?";
        Question question;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, quizId);
            statement.setInt(2, index);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            rs.next();
            String title = rs.getString("title");
            int questionId = rs.getInt("id");
            question = new Question(title, questionId);
        }


        sql = "select answers.id, answer, correct from answers left join questions on question_id = questions.id  where question_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, question.getId());
            statement.execute();
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("id");
                String text = rs.getString("answer");
                boolean correct = rs.getBoolean("correct");
                Answer answer = new Answer(id, text, correct);
                question.addAnswer(answer);
            }
        }
        return question;
    }

    public void passAnswer(String userName, int answerId) throws SQLException {
        String sql = "INSERT INTO solutions (user_name, answer_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userName);
            statement.setInt(2, answerId);
            statement.executeUpdate();
        }
    }

    public Solution getSolution(String userName, int quizId) throws SQLException {
        String sql = "SELECT title, answer, question_id, answer_id, correct FROM solutions\n" +
            "LEFT JOIN answers ON solutions.answer_id = answers.id\n" +
            "LEFT JOIN questions ON answers.question_id = questions.id\n" +
            "WHERE user_name = ? AND quiz_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userName);
            statement.setInt(2, quizId);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            Solution solution = new Solution(quizId, "Sample Quiz Name (Work in progress)");
            while (rs.next()) {
                String title = rs.getString("title");
                String answer = rs.getString("answer");
                int questionId = rs.getInt("question_id");
                int answerId = rs.getInt("answer_id");
                boolean correct = rs.getBoolean("correct");
                Question question = new Question(title, questionId);
                question.addAnswer(new Answer(answerId, answer, correct));
                solution.addQuestion(question);

            }
            return solution;
        }


    }

    public List<Quiz> getCompletedQuizIds(String userName) {
        return new ArrayList<>();
    }

    public Quiz getQuizById(int quizId) throws SQLException {
        String sql = "SELECT * FROM quizzes WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, quizId);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            if (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                Quiz quiz = new Quiz(id, title, 0);
                return quiz;
            } else {
                throw new SQLException("Something happened!");
            }

        }
    }

    public List<Quiz> getAllQuizzes() {
        return new ArrayList<>();
    }

    public List<Integer> getQuizIdsByLevel(int lvl) {
        return new ArrayList<>();
    }

    public int countQuestions(int quizId) throws SQLException {
        String sql = "select count(id) from questions where quiz_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, quizId);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            if (rs.next()) {
                return rs.getInt("count");
            } else {
                throw new SQLException("Something happened!");
            }
        }
    }

    public int countAnsweredQuestions(String userName, int quizId) throws SQLException {
        String sql = "select count(solutions.answer_id) from solutions " +
            "inner join answers on solutions.answer_id = answers.id " +
            "inner join questions on answers.question_id = questions.id " +
            "where questions.quiz_id = ? and solutions.user_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, quizId);
            statement.setString(2, userName);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            if (rs.next()) {
                return rs.getInt("count");
            } else {
                throw new SQLException("Something happened!");
            }
        }
    }

    public QuizEvaluation getQuizEvaluation(String userName, int quizId) throws SQLException {
        String sql = "SELECT status FROM evaluations WHERE user_name=? and quiz_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userName);
            statement.setInt(2, quizId);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            if (rs.next()) {
                int status = rs.getInt("status");
                return convertNumToQuizEvaluation(status);
            }
            return null;
        }
    }

    public Map<Integer, QuizEvaluation> getEvaluationForAllQuizzes(String userName) throws SQLException {
        String sql = "SELECT status FROM evaluations WHERE user_name=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userName);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            Map<Integer, QuizEvaluation> quizEvalMap = new HashMap<>();
            while (rs.next()) {
                int status = rs.getInt("status");
                QuizEvaluation quizEval = convertNumToQuizEvaluation(status);
                int quizId = rs.getInt("quiz_id");
                quizEvalMap.put(quizId, quizEval);

            }
            return quizEvalMap;
        }

    }


    public void setQuizEvaluation(String userName, int quizId, QuizEvaluation quizEvaluation) throws SQLException {
        String sql = "INSERT INTO evaluations (user_name, quiz_id, status) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userName);
            statement.setInt(2, quizId);
            int status = convertQuizEvaluationToNum(quizEvaluation);
            statement.setInt(3, status);
            try {
                statement.executeUpdate();
            } catch (SQLException e) {
                updateQuizEvaluation(userName, quizId, quizEvaluation);
            }
        }
    }

    private void updateQuizEvaluation(String userName, int quizId, QuizEvaluation quizEvaluation) throws SQLException {
        String sql = "UPDATE evaluations SET status=? WHERE user_name=? and quiz_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, convertQuizEvaluationToNum(quizEvaluation));
            statement.setString(2, userName);
            statement.setInt(3, quizId);
            statement.executeUpdate();
        }
    }

    private int convertQuizEvaluationToNum(QuizEvaluation quizEvaluation) {
        switch (quizEvaluation) {
            case FAILED:
                return -1;
            case STARTED:
                return 0;
            case FINISHED:
                return 1;
            case PASSED:
                return 2;
            default:
                throw new IllegalArgumentException();
        }
    }

    private QuizEvaluation convertNumToQuizEvaluation(int num) {
        switch (num) {
            case -1:
                return QuizEvaluation.FAILED;
            case 0:
                return QuizEvaluation.STARTED;
            case 1:
                return QuizEvaluation.FINISHED;
            case 2:
                return QuizEvaluation.PASSED;
            default:
                throw new IllegalArgumentException();
        }
    }


}
