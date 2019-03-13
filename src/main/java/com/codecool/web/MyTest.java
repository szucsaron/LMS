package com.codecool.web;

import com.codecool.web.model.Content;
import com.codecool.web.model.quiz.Question;
import com.codecool.web.model.quiz.Quiz;
import com.codecool.web.model.quiz.QuizGenerator;
import com.codecool.web.service.Database;
import com.codecool.web.service.DatabaseLoader;

import java.io.IOException;
import java.util.*;

public class MyTest {
    public static void main(String[] args) {
        try {
            DatabaseLoader databaseLoader = new DatabaseLoader();
            Content content = databaseLoader.loadContent("src/articles.xml", "src/quiz.xml");
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
