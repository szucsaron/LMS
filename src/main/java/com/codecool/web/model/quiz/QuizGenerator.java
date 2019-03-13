package com.codecool.web.model.quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizGenerator {
    public List<Quiz> generate() {
        Quiz quiz = new Quiz("Goats");

        Question actual;
        Question q1 = new Question("How many legs do goats have?");
        actual = q1;
        actual.addAnswer("1");
        actual.addAnswer("2");
        actual.addAnswer("3");
        actual.addAnswer("4");
        actual.setAsCorrect(3);

        Question q2 = new Question("Are goats great?");
        actual = q2;
        actual.addAnswer("Hell yeah!");
        actual.addAnswer("Nay!");
        actual.addAnswer("Sort of");
        actual.addAnswer("It cannot be determined, as goats have no intrinsic values assigned to them by nature");
        actual.setAsCorrect(0);

        Question q3 = new Question("What is the difference between a painting and a goat?");
        actual = q3;
        actual.addAnswer("Nothing. They live in the same physical reality.");
        actual.addAnswer("Nothing. Their names have letters in common.");
        actual.addAnswer("You can paint goats, but you can't goat paints");
        actual.addAnswer("They are nice");
        actual.addAnswer("They both lack things: The goat doesn't have wheels, as a bicycle doesn't have legs.");
        actual.addAnswer("One is the ultimate expression of creativity, spirit and human nature; the exaltation " +
            "of life force itself, the other one is a wall decoration.");
        actual.setAsCorrect(3);

        quiz.addQuestion(q1);
        quiz.addQuestion(q2);
        quiz.addQuestion(q3);

        Quiz quiz2 = new Quiz("Feeding goats");

        Question q4 = new Question("How many foods can you feed a goat?");
        actual = q4;
        actual.addAnswer("5");
        actual.addAnswer("6");
        actual.addAnswer("77");
        actual.addAnswer("1");
        actual.setAsCorrect(0);

        Question q5 = new Question("What can a goat eat?");
        actual = q5;
        actual.addAnswer("Cucumber");
        actual.addAnswer("Void");
        actual.addAnswer("Bastard eats everything");
        actual.addAnswer("Diesel fuel");
        actual.setAsCorrect(2);

        Question q6 = new Question("What can eat a goat?");
        actual = q6;
        actual.addAnswer("Animals that don't eat goats");
        actual.addAnswer("Animals that eat goats");
        actual.addAnswer("Both");
        actual.addAnswer("None of the above");
        actual.setAsCorrect(1);

        quiz2.addQuestion(q4);
        quiz2.addQuestion(q5);
        quiz2.addQuestion(q6);

        quiz.addQuestion(q4);
        quiz.addQuestion(q5);
        quiz.addQuestion(q6);

        List<Quiz> quizes = new ArrayList<>();
        quizes.add(quiz);
        quizes.add(quiz2);
        return quizes;
    }
}
