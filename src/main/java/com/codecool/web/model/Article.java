package com.codecool.web.model;

import com.codecool.web.model.quiz.Quiz;

public class Article {

    private String title;
    private String text;
    private int level;

    public Quiz getQuiz() {
        return quiz;
    }

    private Quiz quiz;

    public Article(String title, String text, Quiz quiz, int level) {
        this.title = title;
        this.text = text;
        this.level = level;
        this.quiz = quiz;
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
        String out = title + "\n" + text + "\n";
        if (quiz != null) {
            out += quiz.toString();
        }
        return out;
    }

    public boolean hasAccess(User user) {
        return user.getProgress() >= level;
    }

    public void addQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public boolean hasQuiz() {
        return quiz != null;
    }


}
