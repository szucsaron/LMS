package com.codecool.web.model.quiz;

import java.util.Iterator;
import java.util.List;

public class QuestionIterator implements Iterator<Question> {
    private List<Question> questions;
    private int index = 0;

    public QuestionIterator(List<Question> questions) {
        this.questions = questions;
    }

    public Question next() {
        Question question = questions.get(index);
        index++ ;
        return question;
    }

    public boolean hasNext() {
        return index < questions.size();
    }
}
