package com.codecool.web.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Content implements Iterable<Article> {
    private List<Article> articles;

    public Content() {
        articles = new ArrayList<>();
    }

    public Content(List<Article> articles) {
        this.articles = articles;
    }

    public void addArticle(Article article) {
        articles.add(article);
    }

    public List<Article> getAllArticles() {
        return articles;
    }

    public Article getArticle(int id) {
        return articles.get(id);
    }

    public int size() {
        return articles.size();
    }

    public Iterator<Article> iterator() {
        return new ContentsIterator(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Article article : this) {
            sb.append(article);
        }
        return sb.toString();
    }

}
