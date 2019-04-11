package com.codecool.web.service;

import com.codecool.web.dao.ArticleDao;
import com.codecool.web.dao.QuizDao;
import com.codecool.web.model.Article;
import com.codecool.web.model.quiz.Quiz;

import java.sql.SQLException;

public class ArticleEditService {
    private final ArticleDao articleDao;
    private final QuizDao quizDao;

    public ArticleEditService(ArticleDao articleDao, QuizDao quizDao) {
        this.articleDao = articleDao;
        this.quizDao = quizDao;
    }

    public void addArticle(Article article) throws SQLException {
        int quizId = quizDao.createEmptyQuiz(article.getTitle());
        article.setQuizId(quizId);
        articleDao.addArticle(article);
        quizDao.createEmptyQuestion(quizId, 4);
    }

    public void modifyArticle(Article article) throws SQLException {
        articleDao.modifyArticle(article);
    }


}
