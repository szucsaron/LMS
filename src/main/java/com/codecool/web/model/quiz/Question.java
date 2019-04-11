package com.codecool.web.model.quiz;

import java.util.*;

public class Question implements Iterable<Answer>{
    private Map<Integer, Answer> answers = new HashMap<>();
    private String description;
    private int id;

    public int getId() {
        return id;
    }

    public Question(String description, int id) {
        this.description = description;
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addAnswer(Answer answer) {
        answers.put(answer.getId(), answer);
    }


    public boolean validateAnswer(int answerIndex) {
        return answers.get(answerIndex).validate();
    }

    public String getDescription() {
        return description;
    }

    public Iterator<Answer> iterator() {
        Answer[] ans = new Answer[answers.keySet().size()];
        return new AnswerIterator(answers.values().toArray(ans));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(" + id + ")").append(" ").append(description).append( "<br>");
        int id = 0;
        for (int answerId : answers.keySet()) {
            sb  .append("    ")
                .append("(" + answers.get(answerId).getId() + ")")
                .append( " ")
                .append(answers.get(answerId).getText())
                .append("<br>");
            id++;
        }
        return sb.toString();
    }

}
