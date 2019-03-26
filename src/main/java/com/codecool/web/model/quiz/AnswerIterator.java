package com.codecool.web.model.quiz;

import java.util.Iterator;

public class AnswerIterator implements Iterator<Answer> {
    private Answer[]  answers;
    private int index = 0;

    public AnswerIterator(Answer[] answers) {
        this.answers = answers;
    }

    public Answer next() {
        Answer out = answers[index];
        index++;
        return out;
    }

    public boolean hasNext() {
        return answers.length > index;
    }
}
