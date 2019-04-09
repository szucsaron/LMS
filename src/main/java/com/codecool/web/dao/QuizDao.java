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

    public boolean passSolution(int answerId) {
        // Adds a user solution to the database
        return true;
    }

    public Solution getSolution(String userEmail, int quizId) throws SQLException {
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

    public Quiz getQuizById(int quizId) {
        return new Quiz(0, null, 0);
    }

    public List<Quiz> getAllQuizzes() {
        return new ArrayList<>();
    }

    public List<Integer> getQuizIdsByLevel(int lvl) {
        return new ArrayList<>();
    }


}
