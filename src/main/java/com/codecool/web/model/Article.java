package com.codecool.web.model;

import com.codecool.web.model.quiz.Quiz;

public class Article {

    private String title;
    private String text;
    private Integer quizId;

    private int level;

    private int lvlIncrease;

    public Article(Integer id, String title, String text, Integer quizId, int level) {
        this.title = title;
        this.text = text;
        this.level = level;
        this.quizId = quizId;

        this.id = id;
    }

    public Article(String title, String text) {
        this(null, title, text, null, 0);
    }

    public Article(int id, String title, String text) {
        this(id, title, text, null, 0);
    }



    public Integer getId() {
        return id;
    }

    private Integer id;

    public int getLvlIncrease() {
        return lvlIncrease;
    }

    public void setLvlIncrease(int score) {
        this.lvlIncrease = score;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String toString() {
        return String.format("id: %d, quizId: %d, title: %s, text: %s", id, quizId, title, text);
    }

    public boolean hasAccess(User user) {
        int k = user.getProgress();
        return user.getProgress() >= level;
    }

    public void addQuiz(Quiz quiz) {
        this.quizId = quiz.getId();
    }

    public boolean hasQuiz() {
        return quizId != null;
    }

    public Integer getQuizId() {
        return quizId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

}
