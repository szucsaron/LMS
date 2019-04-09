package com.codecool.web.dao;

import com.codecool.web.model.Article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ArticleDao extends AbstractDao {


    public ArticleDao(Connection connection) {
        super(connection);
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
            int quizId = rs.getInt("quiz_id");
            int level = rs.getInt("lvl");
            Article article = new Article(id, title, text, quizId, level);
            article.setLevel(level);
            return article;
        }
    }

    public Map<Integer, String> getArticleIds() throws SQLException {

        return getArticleIdsBySearch("");
    }

    public void addArticle(Article article) throws SQLException {
        String sql = "INSERT INTO articles (title, textcontent, lvl) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, article.getTitle());
            statement.setString(2, article.getText());
            statement.setInt(3, article.getLevel());
            statement.executeUpdate();
        }
    }

    public void modifyArticle(Article article) throws SQLException {
        String sql = "UPDATE articles SET title=?, textcontent=?, lvl=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, article.getTitle());
            statement.setString(2, article.getText());
            statement.setInt(3, article.getLevel());
            statement.setInt(4, article.getId());
            statement.executeUpdate();
        }
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
}
