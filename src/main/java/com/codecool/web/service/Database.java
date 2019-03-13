package com.codecool.web.service;

import com.codecool.web.model.Article;
import com.codecool.web.model.Content;
import com.codecool.web.model.quiz.Quiz;
import com.codecool.web.model.quiz.QuizGenerator;
import com.codecool.web.model.User;

import java.util.*;

public class Database {
    private static Database database = new Database();
    public int score = 0;
    private HashMap<String, User> users = new HashMap<>();

    public static Database getInstance() {
        return database;
    }

    List<User> users_OLD = new ArrayList<>();


    private Content content;

    public Database() {
        content = new Content();
        List<Quiz> quizes = new QuizGenerator().generate();

        content.addArticle(new Article("What is a goat?", "A goat is a mammal with all the hooves and none of the decency.", quizes.get(0), 1));
        content.addArticle(new Article("Goat breeding", "Try to raise your number of goats. \n " +
            "Hint: Add goats to make more goats", quizes.get(1), 2));
        content.addArticle(new Article("Teaching your goats moral nihilism", "You can teach your goats the base values of " +
            "moral nihilism like this: <br>" +
            "public class MyGoat() extends Goat {" +
            "   private Boolean goodOrBad = null;" +
            "}", quizes.get(0), 3));


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

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public User getUserByName(String userName) {
        return users.get(userName);
    }

    public User[] getUsersArray() {
        return users.values().toArray(new User[users.size()]);
    }

    public Set<String> getUserNames() {
        return users.keySet();
    }

}
