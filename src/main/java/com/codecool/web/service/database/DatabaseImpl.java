package com.codecool.web.service.database;

import com.codecool.web.model.Article;
import com.codecool.web.model.NoSuchUserException;
import com.codecool.web.model.User;
import com.codecool.web.model.quiz.Question;
import com.codecool.web.model.quiz.Quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DatabaseImpl implements Database {

    private final Connection connection;

    public DatabaseImpl(Connection connection) {
        this.connection = connection;
    }


    public List<String> doStuff() throws SQLException {
        String sql = "SELECT * FROM articles";
        List<String> titles = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                titles.add(title);
            }
        }
        return titles;
    }


/*
    public List<Coupon> find(int userId, int shopId) throws SQLException {
        String sql = "SELECT id, name, percentage FROM coupons  LEFT JOIN coupons_shops ON coupons.id = coupon_id WHERE user_id = ? and shop_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            List<Coupon> coupons = new ArrayList<>();
            statement.setInt(1, userId);
            statement.setInt(2, shopId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                coupons.add(fetchCoupon(resultSet));
            }

            return coupons;
        }
    }
*/

    public void setLocation(String locationPrefix) {

    }

    public Article getArticle(int id) throws SQLException {
        String sql = "SELECT * FROM articles WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            rs.next();
            String title = rs.getString("title");
            String text = rs.getString("textcontent");
            Article article = new Article(id, title, text);
            return article;
        }
    }

    public Map<Integer, String> getArticleIds() throws SQLException {

        return getArticleIdsBySearch("");
    }

    public void addArticle(Article article) {

    }

    public void modifyArticle(Article article) {

    }

    public Map<Integer, String> getArticleIdsBySearch(String toFind) throws SQLException {
        toFind += "%";
        String sql = "SELECT id, title FROM articles WHERE title like ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, toFind);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            Map<Integer, String> ids = new HashMap<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                ids.put(id, title);
            }

            return ids;
        }
    }

    public void addUser(User user) {

    }

    public User getUserByName(String userName) throws NoSuchUserException {
        return new User();
    }

    public User[] getUsersArray() {
        return new User[0];
    }

    public Set<String> getUserNames() {
        return new HashSet<>();
    }

    public Question getQuestionByQuizAndIndex(int quizId, int index) {
        return new Question(null, 0);
    }

    public Quiz getQuizById(int quizId) {
        return new Quiz(0, null);
    }

    public List<Quiz> getAllQuizzes() {
        return new ArrayList<>();
    }

    public List<Integer> getQuizIdsByLevel(int lvl) {
        return new ArrayList<>();
    }

}
