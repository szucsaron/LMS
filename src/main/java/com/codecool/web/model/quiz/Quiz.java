package com.codecool.web.model.quiz;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Quiz implements Iterable<Question>{
    private List<Question> questions = new ArrayList<>();

    private String description;
    private int mistakeLimit = 1;

    public String getDescription() {
        return description;
    }

    public Quiz(String description) {
        this.description = description;

    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public Iterator<Question> iterator () {
        return new QuestionIterator(questions);
    }


    public boolean validateSuccess(int score) {
        return score >= questions.size() - mistakeLimit;
    }

    public Question getQuestion(int id) {
            return questions.get(id);
    }

    public int size() {
        return questions.size();
    }

    public String toString () {
        String out = description + "\n";
        for (Question question : this) {
            out += question;
        }
        return out;
    }

}
