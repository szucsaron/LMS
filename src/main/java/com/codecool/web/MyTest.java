package com.codecool.web;


import com.codecool.web.model.Content;
import com.codecool.web.dao.DatabaseLoader;
import com.codecool.web.dao.SqlGenerator;

import java.io.*;

public class MyTest {
    public static void main(String[] args) throws IOException{
        DatabaseLoader databaseLoader = new DatabaseLoader();

        Content content = databaseLoader.loadContent("src/main/webapp/articles.xml", "src/main/webapp/quizzes.xml");
        SqlGenerator sqlGenerator = new SqlGenerator(content);
        sqlGenerator.generate();



    }
}
