package com.codecool.web.service;

import com.codecool.web.model.Article;
import com.codecool.web.model.Content;
import com.codecool.web.model.NoSuchUserException;
import com.codecool.web.model.quiz.Quiz;
import com.codecool.web.model.quiz.QuizGenerator;
import com.codecool.web.model.User;

import java.io.IOException;
import java.util.*;

public class Database {
    private String locationPrefix;
    private static Database database = new Database();
    public int score = 0;
    private HashMap<String, User> users = new HashMap<>();

    public static Database getInstance() {
        return database;
    }

    private Content content;

    public Database() {
        List<Quiz> quizes = new QuizGenerator().generate();
        content = new Content();

        DatabaseLoader databaseLoader = new DatabaseLoader();


        try {
            content = databaseLoader.loadContent(locationPrefix + "articles.xml", locationPrefix + "quizzes.xml");
        } catch (IOException e) {
            System.out.println("asfsd");
        }
    }

    public void setLocation(String locationPrefix) {
        this.locationPrefix = locationPrefix;
        DatabaseLoader databaseLoader = new DatabaseLoader();
        try {
            content = databaseLoader.loadContent(locationPrefix + "articles.xml", locationPrefix + "quizzes.xml");
        } catch (IOException e) {
            System.out.println("asfsd");
        }
    }

    public Content getAllContent() {
        return content;
    }

    public Article getArticle(int id) {
        return content.getArticle(id);
    }

    public Map<Integer, String> getArticleIds() {
        Map<Integer, String> ids = new HashMap<>();
        int size = content.size();
        for (int i = 0; i < size; i++) {
            ids.put(i, content.getArticle(i).getTitle());
        }
        return ids;
    }

    public void addArticle(Article article) {
        content.addArticle(article);
    }

    public Map<Integer, String> getFilteredArticleIds(String toFind) {
        Map<Integer, String> mapToFilter = getArticleIds();
        Map<Integer, String> filteredMap = new HashMap<>();
        for (int key : mapToFilter.keySet()) {
            if (mapToFilter.get(key).contains(toFind)) {
                filteredMap.put(key, mapToFilter.get(key));
            }
        }
        return filteredMap;
    }

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public User getUserByName(String userName) throws NoSuchUserException {

        User u = users.get(userName);
        if (u == null) {
            throw new NoSuchUserException();
        }
        return u;
    }

    public User[] getUsersArray() {
        return users.values().toArray(new User[users.size()]);
    }

    public Set<String> getUserNames() {
        return users.keySet();
    }

}
