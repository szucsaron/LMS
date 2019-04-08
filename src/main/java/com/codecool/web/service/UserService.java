package com.codecool.web.service;

import com.codecool.web.model.NoSuchUserException;
import com.codecool.web.model.User;
import com.codecool.web.service.database.Database;
import com.codecool.web.service.database.MockDatabase;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class UserService {

    Database database = MockDatabase.getInstance();

    public User[] getUsers() {
        return database.getUsersArray();
    }

    public Map<String, List<Integer>> getCommittedTests() {
        Map<String, List<Integer>> committedTests = new HashMap<>();
        for (User u : getUsers()) {
            if (u.getRole().equals("STUDENT")) {
                committedTests.put(u.getUsername(), u.getFilledTests());
            }
        }
        return committedTests;
    }

    public void addUser(User user) {
        database.addUser(user);
    }

    public void validateLogIn(String username, String password) throws NoSuchUserException {
        User u = database.getUserByName(username);
        if (!u.getPassword().equals(password)) {
            throw new NoSuchUserException();
        }
    }

    public boolean validateRegistration(String username, String email) {
        return validateUniqueUsername(username) && validateUniqueEmail(email);
    }

    public boolean validateUniqueUsername(String username) {
        return !database.getUserNames().contains(username);
    }

    public boolean validateUniqueEmail(String email) {
        return !database.getEmailAddresses().contains(email);
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
            user = MockDatabase.getInstance().getUserByName(userName);
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
        User guest = new User("guest", "", "", "GUEST");
        guest.setProgress(0);
        return guest;
    }
}
