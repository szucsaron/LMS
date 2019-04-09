package com.codecool.web.dao;

import com.codecool.web.model.User;

import java.sql.Connection;

public class UserDao extends AbstractDao {
    public UserDao(Connection connection) {
        super(connection);
    }


}
