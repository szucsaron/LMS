package com.codecool.web;


import com.codecool.web.model.Content;
import com.codecool.web.service.database.DatabaseLoader;
import com.codecool.web.service.database.SqlGenerator;

import java.io.*;

public class MyTest {
    public static void main(String[] args) throws IOException{
        DatabaseLoader databaseLoader = new DatabaseLoader();

        Content content = databaseLoader.loadContent("src/main/webapp/articles.xml", "src/main/webapp/quizzes.xml");
        SqlGenerator sqlGenerator = new SqlGenerator(content);
        String output = sqlGenerator.generate();

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("goat_init.sql")));
        bufferedWriter.write(output);
        bufferedWriter.close();

    }
}
