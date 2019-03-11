package com.codecool.web;

import com.codecool.web.model.Content;
import com.codecool.web.service.Database;

import java.util.HashMap;
import java.util.Map;

public class MyTest {
    public static void main(String[] args) {
        Database database = Database.getInstance();
        Content content = database.getAllContent();


        System.out.println(content);
        Map<Integer, String>  sss = new HashMap<>();
        sss.put(0, "sdfsdf");
        Object fff = (Object) sss;

        Map<Integer, String> ccc = (Map<Integer, String>) fff;
        System.out.println(ccc.get(0));


    }
}
