package com.codecool.web.model.quiz;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Quiz implements Iterable<Question>{
    private List<Question> questions = new ArrayList<>();

    private String description;
    private int mistakeLimit = 1;
    private int size = 0;

    public int getId() {
        return id;
    }

    private int id;

    public String getDescription() {
        return description;
    }

    public Quiz(int id, String description, int questionSize) {
        this.description = description;
        this.id = id;
        this.size = questionSize;
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
        size++;
    }

    public Iterator<Question> iterator () {
        return new QuestionIterator(questions);
    }


    public boolean validateSuccess(int score) {
        return score >= questions.size() - mistakeLimit;
    }

    public Question getQuestion(int index) {
            return questions.get(index);
    }

    public int size() {
        return size;
    }

    public String toString () {
        String out = description + "\n";
        for (Question question : this) {
            out += question;
        }
        return out;
    }

    public void removeQuestions() {
        questions = null;
    }

}
