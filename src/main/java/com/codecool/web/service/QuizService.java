package com.codecool.web.service;

import com.codecool.web.dao.QuizDao;
import com.codecool.web.dao.SqlGenerator;
import com.codecool.web.dao.UserDao;
import com.codecool.web.model.User;
import com.codecool.web.model.quiz.Question;
import com.codecool.web.model.quiz.Quiz;
import com.codecool.web.model.quiz.QuizEvaluation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public boolean isOver(User user, int quizId) throws SQLException {
        int userAnswerCount = quizDao.countAnsweredQuestions(user.getUsername(), quizId);
        int questionsInQuizCount = quizDao.countQuestions(quizId);
        return userAnswerCount == questionsInQuizCount;
    }

    public void markQuizForEvaluation(Quiz quiz, User user) throws SQLException {
        quizDao.setQuizEvaluation(user.getUsername(), quiz.getId(), QuizEvaluation.FINISHED);
    }

    public void markQuizForStart(Quiz quiz, User user) throws SQLException {
        quizDao.setQuizEvaluation(user.getUsername(), quiz.getId(), QuizEvaluation.STARTED);
    }

    private Map<Integer, QuizEvaluation> getEvaluationForAllQuizzes(String userName) throws SQLException {
        return quizDao.getEvaluationForAllQuizzes(userName);
    }

    public Map<String, Map<Integer, QuizEvaluation>> getCommittedTests() throws SQLException {
        Map<String, Map<Integer, QuizEvaluation>> committedTests = new HashMap<>();
        for (User u : userDao.getUsers()) {
            if (u.getRole().equals("STUDENT")) {
                String currentUserName = u.getUsername();
                committedTests.put(currentUserName, getEvaluationForAllQuizzes(currentUserName));
            }
        }
        return committedTests;
    }

    public Map<String, List<Integer>> getAllQuizzesWaitingForEval() throws SQLException {
        Map<String, Map<Integer, QuizEvaluation>> all = getCommittedTests();
        Map<String, List<Integer>> result = new HashMap<>();
        for (String userName : all.keySet()) {
            List<Integer> temp = new ArrayList<>();
            for (Integer quizID : all.get(userName).keySet()) {
                if (all.get(userName).get(quizID).equals(QuizEvaluation.FINISHED)) {
                    temp.add(quizID);
                }
            }
            if (temp.size() > 0) {
                result.put(userName, temp);
            }
        }
        return result;
    }

    public List<Integer> getQuizzesWaitingForEval(String userName) throws SQLException {
        return getAllQuizzesWaitingForEval().get(userName);
    }

    public Map<String, List<Integer>> getAllQuizzesPassed() throws SQLException {
        Map<String, Map<Integer, QuizEvaluation>> all = getCommittedTests();
        Map<String, List<Integer>> result = new HashMap<>();
        for (String userName : all.keySet()) {
            List<Integer> temp = new ArrayList<>();
            for (Integer quizID : all.get(userName).keySet()) {
                if (all.get(userName).get(quizID).equals(QuizEvaluation.PASSED)) {
                    temp.add(quizID);
                }
            }
            if (temp.size() > 0) {
                result.put(userName, temp);
            }
        }
        return result;
    }

    public List<Integer> getQuizzesPassed(String userName) throws SQLException {
        return getAllQuizzesPassed().get(userName);
    }

    public Map<String, List<Integer>> getAllQuizzesFailed() throws SQLException {
        Map<String, Map<Integer, QuizEvaluation>> all = getCommittedTests();
        Map<String, List<Integer>> result = new HashMap<>();
        for (String userName : all.keySet()) {
            List<Integer> temp = new ArrayList<>();
            for (Integer quizID : all.get(userName).keySet()) {
                if (all.get(userName).get(quizID).equals(QuizEvaluation.FAILED)) {
                    temp.add(quizID);
                }
            }
            if (temp.size() > 0) {
                result.put(userName, temp);
            }
        }
        return result;
    }
    public List<Integer> getQuizzesFailed(String userName) throws SQLException {
        return getAllQuizzesFailed().get(userName);
    }

    public void modifyQuestion(Question question) {

    }
}
