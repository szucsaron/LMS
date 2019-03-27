package com.codecool.web.service;

import com.codecool.web.model.Article;
import com.codecool.web.model.Content;
import com.codecool.web.model.NoSuchUserException;
import com.codecool.web.model.User;
import com.codecool.web.model.quiz.Answer;
import com.codecool.web.model.quiz.Question;
import com.codecool.web.model.quiz.Quiz;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

        content = new Content();

        DatabaseLoader databaseLoader = new DatabaseLoader();
        User user = new User("jancsi", "1234", "adda@adasd.hu", "STUDENT");
        user.setProgress(1);
        addUser(user);

        try {
            content = databaseLoader.loadContent(locationPrefix + "articles.xml", locationPrefix + "quizzes.xml");
        } catch (IOException e) {
            System.out.println("asfsd");
        }
    }

    public void setLocation(String locationPrefix) {
        if (!locationLoaded) {
            this.locationPrefix = locationPrefix;
            DatabaseLoader databaseLoader = new DatabaseLoader();
            try {
                content = databaseLoader.loadContent(locationPrefix + "articles.xml", locationPrefix + "quizzes.xml");
            } catch (IOException e) {
                System.out.println("asfsd");
            }
            locationLoaded = true;
        }
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

    public Question getQuestionByQuizAndIndex(int quizId, int index) {
        Question question = new Question("What is a bird?", 0);
        question.addAnswer(new Answer(0, "Anyád", false));
        question.addAnswer(new Answer(1, "A kitchen appliance", false));
        question.addAnswer(new Answer(1, "A breed of dog", false));
        question.addAnswer(new Answer(1, "An animal", true));
        return question;
    }


}