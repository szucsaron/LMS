package com.codecool.web.service;

import com.codecool.web.model.Article;
import com.codecool.web.model.Content;

import java.util.List;

public class Database {
    private static Database database = new Database();

    public static Database getInstance() {
        return database;
    }

    private Content content;

    public Database() {
        content = new Content();
        content.addArticle(new Article("What is a goat?", "A goat is a mammal with four legs and soothing vocal calls"));
        content.addArticle(new Article("Goat breeding", "Try to raise your number of goats. \n " +
            "Hint: Add goats to make more goats"));
        content.addArticle(new Article("Teaching your goats moral nihilism", "You can teach your goats the base values of " +
            "moral nihilism like this:" +
            "public class MyGoat() extends Goat {" +
            "   private Boolean goodOrBad = null;" +
            "}"));


    }

    public Content getContent() {
        return content;
    }




}