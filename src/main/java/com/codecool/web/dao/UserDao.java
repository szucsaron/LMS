package com.codecool.web.dao;

import com.codecool.web.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.logging.SimpleFormatter;

public class UserDao extends AbstractDao {
    public UserDao(Connection connection) {
        super(connection);
    }

    public User getUserByName(String name) throws SQLException {
        String sql = "SELECT * FROM users WHERE user_name=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            if (rs.next()) {
                return fetchUser(rs);
            } else {
                return null;
            }
        }
    }

    public User[] getUsers() throws SQLException{
        String sql = "SELECT * FROM users";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
            ResultSet rs = statement.getResultSet();
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                users.add(fetchUser(rs));
            }
            return users.toArray(new User[users.size()]);
        }
    }

    public User getUserByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM users WHERE email=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            if (rs.next()) {
                return fetchUser(rs);
            } else {
                return null;
            }
        }
    }

    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (user_name, passwd, email, role_id, progress) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            int roleId = fetchRoleId(user.getRole());
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setInt(4, roleId);
            statement.setInt(5, user.getProgress());
            statement.executeUpdate();
        }
    }

    public void modifyUser(User user) throws SQLException {
        String sql = "UPDATE users SET passwd = ?, email = ?, role_id = ?, progress = ? WHERE user_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getEmail());
            statement.setInt(3, fetchRoleId(user.getRole()));
            statement.setInt(4, user.getProgress());
            statement.setString(5, user.getUsername());
            statement.executeUpdate();
        }
    }

    public boolean hasAttended(User user, Date date) throws SQLException{
        String sql = "SELECT user_name FROM attendance WHERE user_name=? and present=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = f.format(date);
            statement.setString(1, user.getUsername());
            statement.setString(2, dateStr);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            if (rs.next()) {
                return true;
            }
            return false;
        }
    }

    public void setAttendance(User user, Date date, boolean wasThere) throws SQLException {
        if (wasThere) {
            String sql = "INSERT INTO attendance (present, user_name) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                String dateStr = f.format(date);
                statement.setString(1, dateStr);
                statement.setString(2, user.getUsername());
                statement.executeUpdate();
            }
        } else {
            String sql = "DELETE FROM attendance WHERE present = ? and user_name = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                String dateStr = f.format(date);
                statement.setString(1, dateStr);
                statement.setString(2, user.getUsername());
                statement.execute();
            }
        }
    }

    private User fetchUser (ResultSet rs) throws SQLException{
        String password = rs.getString("passwd");
        String name = rs.getString("user_name");
        String email = rs.getString("email");

        int roleId = rs.getInt("role_id");
        int progress = rs.getInt("progress");
        String role;
        switch (roleId) {
            case 1:
                role = "STUDENT";
                break;
            case 2:
                role = "MENTOR";
                break;
            default:
                role = "GUEST";
        }
        User user = new User(name, password, email, role);
        user.setProgress(progress);
        return user;
    }

    private int fetchRoleId(String role) {
        switch (role) {
            case "STUDENT":
                return 1;
            case "MENTOR":
                return 2;
            default:
                return 0;
        }
    }
}
