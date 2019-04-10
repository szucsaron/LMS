package com.codecool.web.service;

import com.codecool.web.dao.UserDao;
import com.codecool.web.model.NoSuchUserException;
import com.codecool.web.model.User;
import com.codecool.web.dao.Database;
import com.codecool.web.dao.MockDatabase;
import com.codecool.web.model.quiz.QuizEvaluation;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.awt.geom.QuadCurve2D;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User[] getUsers() throws SQLException {
        return userDao.getUsers();
    }



    public void addUser(User user) throws SQLException {
        userDao.addUser(user);
    }

    public boolean validateLogIn(String username, String password) throws SQLException{
        User u = userDao.getUserByName(username);
        if (u != null && u.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public boolean validateRegistration(String username, String email) throws SQLException {
        return validateUniqueUsername(username) && validateUniqueEmail(email);
    }

    public boolean validateUniqueUsername(String username) throws SQLException {
        return userDao.getUserByName(username) == null;

    }

    public boolean validateUniqueEmail(String email) throws SQLException {
        return userDao.getUserByEmail(email) == null;
    }

    public User getCurrentUser(HttpServletRequest req) throws SQLException {
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
        User user;
        user = userDao.getUserByName(userName);

        if (user != null && user.getPassword().equals(passwd)) {
            return user;
        } else {
            return getGuest();
        }
    }

    private User getGuest() {
        User guest = new User("guest", "", "", "GUEST");
        guest.setProgress(0);
        return guest;
    }
}
