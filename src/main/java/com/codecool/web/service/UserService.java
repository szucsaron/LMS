package com.codecool.web.service;

import com.codecool.web.model.User;

import java.util.ArrayList;
import java.util.List;

public final class UserService {

    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public void addUser(String username, String password) {
        users.add(new User(username, password));
    }

    public boolean validateLogIn(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean validateRegistration(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }
}
