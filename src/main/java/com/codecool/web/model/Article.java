package com.codecool.web.model;

public class Article {

    private String title;
    private String text;

    public Article(String title, String text) {
        this.title = title;
        this.text = text;
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
}
