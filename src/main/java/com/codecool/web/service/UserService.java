package com.codecool.web.service;

import com.codecool.web.model.NoSuchUserException;
import com.codecool.web.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

public final class UserService {

    Database database = Database.getInstance();

    public User[] getUsers() {
        return database.getUsersArray();
    }

    public void addUser(String username, String password, String email) {
        database.addUser(new User(username, password, email));
    }

    public boolean validateLogIn(String username, String password) {
        User u = database.getUserByName(username);
        if (u.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public boolean validateRegistration(String username) {
        return !database.getUserNames().contains(username);
    }

    public User getCurrentUser(HttpServletRequest req) {

        Cookie[] cookies = req.getCookies();

        String debug = "";
        String userName;
        String passwd;
        for (Cookie cookie : cookies) {
            String name = cookie.getName() + " ";
            String value = cookie.getValue() + "\n";
            if (name == "name") {
                userName = value;
            } else if (name=="password") {
                passwd = value;
            }

        }

        Database database = Database.getInstance();
        User user = database.getUser(name);
        try {
        } catch (NoSuchUserException e) {
            user = new User("guest", "", "");
            user.setProgress(0);
        }


        return new User("asds", "asasd", "adas");
    }
}
