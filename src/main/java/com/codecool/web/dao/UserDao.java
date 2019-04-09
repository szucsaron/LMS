package com.codecool.web.dao;

import com.codecool.web.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

                String password = rs.getString("passwd");
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
            } else {
                return null;
            }

        }
    }
}
