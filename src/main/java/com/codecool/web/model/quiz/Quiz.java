package com.codecool.web.model.quiz;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Quiz implements Iterable<Question>{
    private List<Question> questions = new ArrayList<>();

    private String description;
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


    public Question getQuestion(int index) {
            return questions.get(index);
    }

    public int size() {
        return size;
    }

    public String toString () {
        StringBuilder sb = new StringBuilder();
        sb.append(description).append( "\n");
        for (Question question : this) {
            sb.append(question);
        }
        return sb.toString();
    }

    public void removeQuestions() {
        questions = null;
    }

}
