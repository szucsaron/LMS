package com.codecool.web.service;

import com.codecool.web.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
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

    public User getCurrentUser(HttpServletRequest req) {
        /*
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
    }
    */
        return new User("asds", "asasd", "adas");
    }
}
