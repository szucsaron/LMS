package com.codecool.web.dao;

import com.codecool.web.model.quiz.Answer;
import com.codecool.web.model.quiz.Question;
import com.codecool.web.model.quiz.Quiz;
import com.codecool.web.model.quiz.Solution;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public void passAnswer(String userName, int answerId) throws SQLException{
        String sql = "INSERT INTO solutions (user_name, answer_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userName);
            statement.setInt(2, answerId);
            statement.executeUpdate();
        }
    }

    public Solution getSolution(String userName, int quizId) throws SQLException {
        Solution solution = new Solution(0, "What is good goat?");
        Question question = new Question("Is it good?", 0);
        question.addAnswer(new Answer(0, "Yes", true));

        Question question1 = new Question("What are birds", 1);
        question1.addAnswer(new Answer(1, "Trees", false));

        Question question2 = new Question("What is 2 + 2", 2);
        question2.addAnswer(new Answer(2, "4", true));
        solution.addQuestion(question);
        solution.addQuestion(question1);
        solution.addQuestion(question2);
        return solution;
    }

    public List<Quiz> getCompletedQuizIds(String userEmail) {
        return new ArrayList<>();
    }

    public Quiz getQuizById(int quizId) throws SQLException{
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
            } else  {
                throw new SQLException("Something happened!");
            }
        }
    }

    public int countAnsweredQuestions(String userName, int quizId) throws SQLException{
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
            } else  {
                throw new SQLException("Something happened!");
            }
        }
    }


}
