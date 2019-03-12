package com.codecool.web.model;

public final class User {

    private String username;
    private String password;
    private final String email;
    private int progress;

    public User() {
        this.username = "default";
        this.password = "default";
        this.email = "default";
        this.progress = 1;
    }

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

    public String getEmail() { return this.email; }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getProgress() { return progress;}

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
