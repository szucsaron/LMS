package com.codecool.web.service;

import com.codecool.web.model.Article;
import com.codecool.web.model.quiz.Question;

import java.util.HashMap;
import java.util.Map;

public class AlmostRealDatabase {
    private Map<Integer, Question> questions = new HashMap<>();
    private Map<Integer, Article> articles = new HashMap<>();

    public void addQuestion(Question question) {
        questions.put(question.getId(), question);
    }

    public void addArticle(Article article) {
        articles.put(article.getId(), article);
    }


}
