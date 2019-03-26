package com.codecool.web.model.quiz;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Question implements Iterable<String>{
    private List<String> answers = new ArrayList<>();
    private String description;
    private int correctAnswer;
    private int id;

    public int getId() {
        return id;
    }

    public Question(String description, int id) {
        this.description = description;
        this.id = id;
    }

    public void addAnswer(String answer) {
        answers.add(answer);
    }

    public void setAsCorrect(int answerIndex) {
        correctAnswer = answerIndex;
    }

    public boolean validateAnswer(int answerIndex) {
        return answerIndex == correctAnswer;
    }

    public String getDescription() {
        return description;
    }

    public Iterator<String> iterator() {
        return new AnswerIterator(answers);
    }

    public String toString() {
        String out = description + "\n";
        int id = 0;
        for (String answer : answers) {
            if (id == correctAnswer) {
                out += "--->" + answer + "\n";
            } else {
                out += "    " + answer + "\n";
            }
            id++;
        }
        return out;
    }

}
