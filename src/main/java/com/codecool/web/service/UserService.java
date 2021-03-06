package com.codecool.web.service;

import com.codecool.web.dao.UserDao;
import com.codecool.web.model.User;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Date;

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

    public boolean validateLogIn(String username, String password) throws SQLException {
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

    public boolean modifyUser(User user) throws SQLException {
        userDao.modifyUser(user);
        return true;
    }

    public boolean hasAttended(User user, Date date) throws SQLException {
        return userDao.hasAttended(user, date);
    }

    public void setAttendance(User user, Date date, boolean wasThere) throws SQLException {
        userDao.setAttendance(user, date, wasThere);
    }

    public User getUserByName(String userName) throws SQLException {
        return userDao.getUserByName(userName);
    }

    private User getGuest() {
        User guest = new User("guest", "", "", "GUEST");
        guest.setProgress(0);
        return guest;
    }
}
