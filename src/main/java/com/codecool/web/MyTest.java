package com.codecool.web;

import com.codecool.web.model.Content;

import com.codecool.web.service.Database;
import com.codecool.web.service.DatabaseLoader;

import java.io.IOException;

public class MyTest {
    public static void main(String[] args) throws IOException{

            Database database = Database.getInstance();
            Content content = database.getAllContent();
            System.out.println(content);



    }
}
