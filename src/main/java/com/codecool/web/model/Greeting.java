package com.codecool.web.model;

public final class Greeting {

    private final String text;
    private final String lang;

    public Greeting(String lang, String text) {
        this.lang = lang;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public String getLang() {
        return lang;
    }
}
