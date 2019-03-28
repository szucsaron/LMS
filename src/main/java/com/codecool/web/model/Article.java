package com.codecool.web.model;

import com.codecool.web.model.quiz.Quiz;

public class Article {

    private String title;
    private String text;
    private Integer quizId;

    private int level;

    private int lvlIncrease;

    public Article(Integer id, String title, String text, Quiz quiz, int level) {
        this.title = title;
        this.text = text;
        this.level = level;
        this.quiz = quiz;
        if (quiz != null) {
            this.quizId = quiz.getId();
        } else {
            this.quizId = null;
        }
        this.id = id;
    }

    public Article(String title, String text) {
        this(null, title, text, null, 0);
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

    public Quiz getQuiz() {
        return quiz;
    }

    private Quiz quiz;

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String toString() {
        String out = id + " " + title + "\n" + text + "\n";
        if (quiz != null) {
            out += quiz.toString();
        }
        return out;
    }

    public boolean hasAccess(User user) {
        int k = user.getProgress();
        return user.getProgress() >= level;
    }

    public void addQuiz(Quiz quiz) {
        this.quizId = quiz.getId();
        this.quiz = quiz;
    }

    public boolean hasQuiz() {
        return quizId != null;
    }

    public Integer getQuizId() {
        return quizId;
    }

}
