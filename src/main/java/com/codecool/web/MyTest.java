package com.codecool.web;


import com.codecool.web.model.Article;
import com.codecool.web.model.Content;
import com.codecool.web.model.quiz.Answer;
import com.codecool.web.model.quiz.Question;
import com.codecool.web.model.quiz.Quiz;
import com.codecool.web.service.Database;
import com.codecool.web.service.DatabaseLoader;
import com.codecool.web.service.MockDatabase;

import java.io.IOException;

public class MyTest {
    public static void main(String[] args) throws IOException{
        DatabaseLoader databaseLoader = new DatabaseLoader();

        Content content = databaseLoader.loadContent("src/main/webapp/articles.xml", "src/main/webapp/quizzes.xml");
        Database db = new MockDatabase(content);
        Question question = db.getQuestionByQuizAndIndex(0, 0);
        System.out.println(question);

    }
}
