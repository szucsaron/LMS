package com.codecool.web.model.quiz;

import java.util.Iterator;
import java.util.List;

public class AnswerIterator implements Iterator<String> {
    private List<String> answers;
    private int index = 0;

    public AnswerIterator(List<String> answers) {
        this.answers = answers;
    }

    public String next() {
        String out = answers.get(index);
        index++;
        return out;
    }

    public boolean hasNext() {
        return answers.size() > index;
    }
}
