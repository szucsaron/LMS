package com.codecool.web.model;

import com.codecool.web.model.quiz.Quiz;

import java.util.Date;
import java.util.HashMap;

public final class User {

    private String username;
    private String password;
    private final String email;
    private int progress;
    private Role role;
    private int score;
    private Quiz actualQuiz;
    private HashMap<Date, Boolean> attendance = new HashMap<>();

    public enum Role {
        GUEST, STUDENT, MENTOR, ADMIN
    }

    public User() {
        this.username = "Guest";
        this.password = "passingby";
        this.email = "guest@guest.com";
        this.progress = 0;
        this.role = Role.GUEST;
        this.score = 0;
    }

    public User(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.progress = 1;
        this.role = Role.valueOf(role);
        this.score = 0;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email; }

    public String getRole() {
        return String.valueOf(this.role);
    }

    public int getScore() {
        return score;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    int getProgress() {
        return progress;}

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public void incrementScore() {
        score += 1;
    }

    public void setAttendance(Date date, Boolean wasHere) {
        attendance.put(date, wasHere);
    }

    public Boolean getAttendance(Date date) {
        return attendance.containsKey(date) ? attendance.get(date) : Boolean.FALSE;
    }

    public void setRole(String type) {
        this.role = Role.valueOf(type);
    }

    public void beginQuiz(Quiz quiz) {
        actualQuiz = quiz;
    }

    public void endQuiz(int lvlIncrease) {
        progress += lvlIncrease;
        actualQuiz = null;
        score = 0;
    }

    public boolean quizStarted() {
        return actualQuiz != null;
    }

    public boolean validateQuiz(Quiz quiz) {
        return true;
    }
}
