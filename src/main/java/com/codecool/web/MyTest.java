package com.codecool.web;

import com.codecool.web.model.Content;

import com.codecool.web.service.DatabaseLoader;

import java.io.IOException;

public class MyTest {
    public static void main(String[] args) {
        try {
            DatabaseLoader databaseLoader = new DatabaseLoader();
            Content content = databaseLoader.loadContent("src/articles.xml", "src/quizzes.xml");
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
