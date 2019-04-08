/*package com.codecool.web;


import com.codecool.web.model.Content;
import com.codecool.web.service.database.DatabaseLoader;
import com.codecool.web.service.database.SqlGenerator;

import java.io.IOException;

public class MyTest {
    public static void main(String[] args) throws IOException{
        DatabaseLoader databaseLoader = new DatabaseLoader();

        Content content = databaseLoader.loadContent("src/main/webapp/articles.xml", "src/main/webapp/quizzes.xml");
        SqlGenerator sqlGenerator = new SqlGenerator(content);
        System.out.println(sqlGenerator.generate());

    }
}*/
