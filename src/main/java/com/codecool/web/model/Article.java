package com.codecool.web.model;

import com.codecool.web.model.quiz.Quiz;

public class Article {

    private String title;
    private String text;
    private int level;
    private Quiz quiz;

    public Article(String title, String text, Quiz quiz, int level) {
        this.title = title;
        this.text = text;
        this.level = level;
    }

    public Article(String title, String text) {
        this(title, text, null, 0);
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String toString() {
        return title + "\n" + text + "\n\n";
    }

    public boolean hasAccess(User user) {
        return user.getProgress() >= level;
    }




}
