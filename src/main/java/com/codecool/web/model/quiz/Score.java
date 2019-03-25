package com.codecool.web.model.quiz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Score {
    private Map<Integer, List<Answer>> answerMap = new HashMap<>();

    public void addAnswer(int quizId, Answer answer) {
        List<Answer> answers = answerMap.get(quizId);
        if (answers == null) {
            answers = new ArrayList<>();
        }
        answers.add(answer);
    }

    public List<Answer> getAnswers(int quizId) {
        return answerMap.get(quizId);
    }
}
