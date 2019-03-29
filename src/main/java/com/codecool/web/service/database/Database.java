package com.codecool.web.service.database;

import com.codecool.web.model.Article;
import com.codecool.web.model.Content;
import com.codecool.web.model.NoSuchUserException;

import com.codecool.web.model.User;
import com.codecool.web.model.quiz.Question;
import com.codecool.web.model.quiz.Quiz;

import java.io.IOException;
import java.util.*;

public interface Database {
    void setLocation(String locationPrefix);

    Article getArticle(int id);

    Map<Integer, String> getArticleIds();

    void addArticle(Article article);

    void modifyArticle(Article article);

    Map<Integer, String> getArticleIdsBySearch(String toFind);

    void addUser(User user);

    User getUserByName(String userName) throws NoSuchUserException;

    User[] getUsersArray();

    Set<String> getUserNames();

    Question getQuestionByQuizAndIndex(int quizId, int index);

    Quiz getQuizById(int quizId);

    List<Quiz> getAllQuizzes();

    public List<Integer> getQuizIdsByLevel(int lvl);

}
