package com.codecool.web;

import com.codecool.web.model.Content;
import com.codecool.web.service.Database;

public class MyTest {
    public static void main(String[] args) {
        Database database = Database.getInstance();
        Content content = database.getContent();


        System.out.println(content);
    }
}
