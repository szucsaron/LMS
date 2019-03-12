package com.codecool.web;

import com.codecool.web.model.Content;
import com.codecool.web.model.quiz.Question;
import com.codecool.web.model.quiz.Quiz;
import com.codecool.web.model.quiz.QuizGenerator;
import com.codecool.web.service.Database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MyTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        QuizGenerator quizGenerator = new QuizGenerator();

        List<Quiz> quizes = quizGenerator.generate();

        Quiz quiz = quizes.get(0);
        for (Question question : quiz) {
            System.out.println(question.getDescription());
            for (String answer : question) {
                System.out.println("  " + answer);
            }
            System.out.println(question.validateAnswer(scanner.nextInt()));
            System.out.println();
        }

    }
}
