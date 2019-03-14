package com.codecool.web.service;

import com.codecool.web.model.NoSuchUserException;
import com.codecool.web.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


public final class UserService {

    Database database = Database.getInstance();

    public User[] getUsers() {
        return database.getUsersArray();
    }

    public void addUser(String username, String password, String email, int progress) {
        User user = new User(username, password, email);
        user.setProgress(progress);
        database.addUser(user);
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

    public User getCurrentUser(HttpServletRequest req) {

        Cookie[] cookies = req.getCookies();
        if (cookies == null) {
            return getGuest();
        }

        String userName = "";
        String passwd = "";
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            String value = cookie.getValue();
            if (name.equals("name")) {
                userName = value;
            } else if (name.equals("password")) {
                passwd = value;
            }
        }
        try {
            User user;
            user = Database.getInstance().getUserByName(userName);
            if (user.getPassword().equals(passwd)) {
                return user;
            } else {
                return getGuest();
            }
        } catch (NoSuchUserException e) {
            return getGuest();
        }
    }

    private User getGuest() {
        User guest = new User("guest", "", "");
        guest.setRole("GUEST");
        guest.setProgress(0);
        return guest;
    }

}
