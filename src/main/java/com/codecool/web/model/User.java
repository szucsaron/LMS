package com.codecool.web.model;

import com.codecool.web.model.quiz.Quiz;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public final class User {

    private String username;
    private String password;
    private final String email;
    private int progress;
    private Role role;
    private int score;
    private Quiz actualQuiz;
    private List<Integer> filledTests = new ArrayList<>();
    private List<Integer> okTests = new ArrayList<>();
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
        this.role = Role.valueOf(role);
        if (role.equals("MENTOR")) {
            progress = 666;
        } else {
            progress = 1;
        }
        this.score = 0;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

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

    public int getProgress() {
        return progress;
    }

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
        return attendance.getOrDefault(date, Boolean.FALSE);
    }

    public void setRole(String type) {
        this.role = Role.valueOf(type);
    }

    public void beginQuiz(Quiz quiz) {
        actualQuiz = quiz;
    }

    public void endQuiz(int lvlIncrease) {
        filledTests.add(actualQuiz.getId());
        progress += lvlIncrease;
        actualQuiz = null;
        score = 0;
    }

    public List<Integer> getFilledTests() {
        return filledTests;
    }

    public List<Integer> getOkTests() {
        return okTests;
    }

    public boolean quizStarted() {
        return actualQuiz != null;
    }

    public boolean validateQuiz(Quiz quiz) {
        return true;
    }

    public String toString() {
        return String.format("name: %s, email: %s, password: %s", username, email, password);
    }
}
