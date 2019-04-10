package com.codecool.web.dao;

import com.codecool.web.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
            int roleId;
            switch (user.getRole()) {
                case "STUDENT":
                    roleId = 1;
                    break;
                case "MENTOR":
                    roleId = 2;
                    break;
                default:
                    roleId = 0;
            }
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setInt(4, roleId);
            statement.setInt(5, user.getProgress());
            statement.executeUpdate();
        }
    }

    public boolean hasAttended(User user, Date date) throws SQLException{
        String sql = "SELECT user_name FROM attendance WHERE user_name=? and present=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());



        }
        return true;
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




}
