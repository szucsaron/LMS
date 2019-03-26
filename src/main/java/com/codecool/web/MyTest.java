package com.codecool.web;


import com.codecool.web.model.Content;
import com.codecool.web.model.quiz.Answer;
import com.codecool.web.model.quiz.Question;
import com.codecool.web.model.quiz.Quiz;
import com.codecool.web.service.DatabaseLoader;

import java.io.IOException;

public class MyTest {
    public static void main(String[] args) throws IOException{
        DatabaseLoader databaseLoader = new DatabaseLoader();

        Content content = databaseLoader.loadContent("src/main/webapp/articles.xml", "src/main/webapp/quizzes.xml");
        Quiz quiz = content.getArticle(0).getQuiz();
        Question question = quiz.getQuestion(0);
        System.out.println(content);

        System.out.println(question.validateAnswer(3));

    }
}
