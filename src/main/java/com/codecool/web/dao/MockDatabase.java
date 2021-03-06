package com.codecool.web.dao;

import com.codecool.web.model.Article;
import com.codecool.web.model.Content;
import com.codecool.web.model.NoSuchUserException;
import com.codecool.web.model.User;
import com.codecool.web.model.quiz.Question;
import com.codecool.web.model.quiz.Quiz;

import java.io.IOException;
import java.util.*;

public class MockDatabase implements Database {
    private String locationPrefix;
    private boolean  locationLoaded = false;
    private static MockDatabase database = new MockDatabase();
    private HashMap<String, User> users = new HashMap<>();

    public static MockDatabase getInstance() {
        return database;
    }

    private Content content;

    public MockDatabase() {


        DatabaseLoader databaseLoader = new DatabaseLoader();
        User jancsi = new User("Jancsi", "1234", "student@codecool.com", "STUDENT");
        User ludovikusz = new User("Ludovikusz", "1234", "student@codecool.com", "STUDENT");
        ludovikusz.setProgress(3);

        User erzsi = new User("Erzsi", "1234", "student@codecool.com", "STUDENT");
        erzsi.setProgress(6);
        User mentor = new User("Pali", "1234", "mentor@codecool.com", "MENTOR");
        addUser(jancsi);
        addUser(ludovikusz);
        addUser(erzsi);
        addUser(mentor);

        try {
            content = databaseLoader.loadContent(locationPrefix + "articles.xml", locationPrefix + "quizzes.xml");
        } catch (IOException e) {
        }
    }

    public MockDatabase(Content content) {
        this.content = content;
    }

    public void setLocation(String locationPrefix) {
        if (!locationLoaded) {
            this.locationPrefix = locationPrefix;
            DatabaseLoader databaseLoader = new DatabaseLoader();
            try {
                content = databaseLoader.loadContent(locationPrefix + "articles.xml", locationPrefix + "quizzes.xml");
            } catch (IOException e) {
            }
            locationLoaded = true;
        }
    }

    public Article getArticle(int id) {
        return content.getArticles().get(id);
    }

    public Map<Integer, String> getArticleIds() {
        Map<Integer, String> ids = new HashMap<>();
        Set<Integer> keys = content.getArticles().keySet();
        for (int key : keys) {
            ids.put(key, content.getArticles().get(key).getTitle());
        }
        return ids;
    }

    public void addArticle(Article article) {
        int maxId = 0;
        Map<Integer, Article> articles = content.getArticles();
        for (int id : articles.keySet()) {
            if (id > maxId) {
                maxId = id;
            }
        }
        maxId++;
        article.setId(maxId);
        content.getArticles().put(maxId, article);
    }

    public Map<Integer, String> getArticleIdsBySearch(String toFind) {
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
        User f = user;
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

    public List<String> getEmailAddresses() {
        List<String> addresses = new ArrayList<>();
        for (User u : users.values()) {
            addresses.add(u.getEmail());
        }
        return addresses;
    }

    public Question getQuestionByQuizAndIndex(int quizId, int index) {
        return content.getQuestionsByQuizIndex(quizId, index);
    }

    public Quiz getQuizById(int quizId) {

        return content.getQuizzes().get(quizId);
    }

    public List<Quiz> getAllQuizzes() {
        List<Quiz> quizlist = new ArrayList<>();
        for (Quiz quiz : content.getQuizzes().values()) {
            quizlist.add(quiz);
        }
        return quizlist;
    }

    public List<Integer> getQuizIdsByLevel(int lvl) {
        List<Integer> quizIds = new ArrayList<>();
        for (Article article : content.getArticles().values()) {
            if (article.getLevel() <= lvl) {
                quizIds.add(article.getQuizId());
            }
        }
        return quizIds;
    }

    public void modifyArticle(Article article) {
        int id = article.getId();

        content.getArticles().put(id, article);
    }

    public void close() {

    }


}
