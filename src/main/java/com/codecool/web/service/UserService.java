package com.codecool.web.service;

import com.codecool.web.model.User;

import java.util.List;

public final class UserService {

    public List<User> getUsers() {
        Database database = Database.getInstance();
        return database.users;
    }

    public void addUser(String username, String password, String email) {
        Database database = Database.getInstance();
        database.users.add(new User(username, password, email));
    }

    public boolean validateLogIn(String username, String password) {
        Database database = Database.getInstance();
        for (User u : database.users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean validateRegistration(String username) {
        Database database = Database.getInstance();
        for (User u : database.users) {
            if (u.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }
}
