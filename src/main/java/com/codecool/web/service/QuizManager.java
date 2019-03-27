package com.codecool.web.service;

import com.codecool.web.model.User;
import com.codecool.web.model.quiz.Question;
import com.codecool.web.model.quiz.Quiz;

public class QuizManager {
    private Quiz quiz;
    private User user;

    public QuizManager(User user, Quiz quiz) {
        this.quiz = quiz;
        this.user = user;
    }

    public void handleNext(Question question, int solution) {
        /*
        Question question;


        question = quiz.getQuestion(questionId);

        if (question.validateAnswer(solution)) {
            user.incrementScore();
        }
        */

    }

}
