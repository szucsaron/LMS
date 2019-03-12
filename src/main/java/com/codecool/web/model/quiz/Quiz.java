package com.codecool.web.model.quiz;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Quiz implements Iterable<Question>{
    private List<Question> questions = new ArrayList<>();
    private String description;
    private int mistakeLimit = 1;

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

}
