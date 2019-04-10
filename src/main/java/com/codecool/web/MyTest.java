package com.codecool.web;


import com.codecool.web.model.Content;
import com.codecool.web.dao.DatabaseLoader;
import com.codecool.web.dao.SqlGenerator;
import com.codecool.web.model.User;

import java.io.*;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.util.Date;

public class MyTest {
    public static void main(String[] args) throws IOException, ParseException {
        DateFormat g = new SimpleDateFormat("MM/dd/yyyy");
        Date chosen = g.parse(g.format(new Date()));

        DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String str = f.format(chosen);
        System.out.println(str);



    }
}
