package com.codecool.web.model;

public final class User {

    private final String username;
    private final String password;
    private final String email;
    private final int progress;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.progress = 0;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}
