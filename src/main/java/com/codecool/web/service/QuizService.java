package com.codecool.web.service;

import com.codecool.web.dao.QuizDao;
import com.codecool.web.dao.UserDao;
import com.codecool.web.model.User;
import com.codecool.web.model.quiz.Question;
import com.codecool.web.model.quiz.Quiz;

import java.sql.SQLException;

public class QuizService {
    private final UserDao userDao;
    private final QuizDao quizDao;

    public QuizService(UserDao userDao, QuizDao quizDao) {
        this.userDao = userDao;
        this.quizDao = quizDao;
    }

    public boolean validateQuiz(User user, int quizId) throws SQLException {
        return true;
    }

    public Question getQuestion(int quizId, int questionIndex) throws SQLException {
        return quizDao.getQuestionByQuizAndIndex(quizId, questionIndex);
    }

    public void passAnswer(String userName, int userId) throws SQLException {
        quizDao.passAnswer(userName, userId);
    }

    public boolean isOver (User user, int quizId) throws SQLException {
        int userAnswerCount = quizDao.countAnsweredQuestions(user.getUsername(), quizId);
        int questionsInQuizCount = quizDao.countQuestions(quizId);
        return userAnswerCount == questionsInQuizCount;
    }
}
