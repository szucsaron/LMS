package com.codecool.web.service;

import com.codecool.web.model.quiz.Question;
import com.codecool.web.model.quiz.Quiz;

public class QuizManager {
    private Quiz quiz;
    private Database database;

    public QuizManager(Database database, Quiz quiz) {
        this.quiz = quiz;
        this.database = database;
    }

    public void handleNext(int questionId, int solution) {
        Question question;


        question = quiz.getQuestion(questionId);

        if (question.validateAnswer(solution)) {
            database.score++;
        }

    }


}
