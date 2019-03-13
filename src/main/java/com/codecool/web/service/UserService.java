package com.codecool.web.service;

import com.codecool.web.model.NoSuchUserException;
import com.codecool.web.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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

    public boolean validateLogIn(String username, String password) throws NoSuchUserException {
        User u = database.getUserByName(username);
        if (u.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public boolean validateRegistration(String username) {
        return !database.getUserNames().contains(username);
    }

    public void getCurrentUser(HttpServletRequest req) {
/*
        Cookie[] cookies = req.getCookies();

        String userName = "";
        String passwd = "";
        for (Cookie cookie : cookies) {
            String name = cookie.getName() + " ";
            String value = cookie.getValue() + "\n";
            if (name.equals("name")) {
                userName = value;
            } else if (name.equals("password")) {
                passwd = value;
            }
        }
        User user = Database.getInstance().getUserByName(userName);
        if (user.getPassword().equals(passwd)) {
            return user;
        } else {
            return getGuest();
        }



    }

    private User getGuest() {
        User guest = new User("guest", "", "");
        guest.setProgress(0);
        return guest;
        return new User("asds", "asasd", "adas");

    */
    }

}
