package com.codecool.web;


import com.codecool.web.model.quiz.Answer;
import com.codecool.web.model.quiz.Score;
import com.codecool.web.service.Database;

import java.io.IOException;

public class MyTest {
    public static void main(String[] args) throws IOException{
        Score score = new Score();
        Answer answer = new Answer();
        score.addAnswer(0, answer);
        System.out.println(score.getAnswers(0));

    }
}
