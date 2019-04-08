package com.codecool.web.dao;

import com.codecool.web.model.Article;
import com.codecool.web.model.Content;
import com.codecool.web.model.NoSuchUserException;

import com.codecool.web.model.User;
import com.codecool.web.model.quiz.Question;
import com.codecool.web.model.quiz.Quiz;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public interface Database extends AutoCloseable{
    void setLocation(String locationPrefix);

    Article getArticle(int id) throws SQLException;

    Map<Integer, String> getArticleIds()  throws SQLException;

    void addArticle(Article article)  throws SQLException;

    void modifyArticle(Article article) throws SQLException;

    Map<Integer, String> getArticleIdsBySearch(String toFind)  throws SQLException;

    void addUser(User user) throws SQLException;

    User getUserByName(String userName) throws NoSuchUserException;

    User[] getUsersArray() throws SQLException;

    Set<String> getUserNames() throws SQLException;

    Question getQuestionByQuizAndIndex(int quizId, int index);

    Quiz getQuizById(int quizId) throws SQLException;

    List<Quiz> getAllQuizzes() throws SQLException;

    List<Integer> getQuizIdsByLevel(int lvl) throws SQLException;

    List<String> getEmailAddresses() throws SQLException;

    @Override
    void close() throws SQLException;
}
