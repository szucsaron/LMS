package com.codecool.web.service;

import com.codecool.web.model.User;

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
}
