package com.codecool.web.model.quiz;

public class Answer {
    private int id;
    private String text;
    private boolean correct;

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Answer(int id, String text, boolean correct) {
        this.id = id;
        this.text = text;
        this.correct = correct;
    }

    public boolean validate() {
        return correct;
    }


}
