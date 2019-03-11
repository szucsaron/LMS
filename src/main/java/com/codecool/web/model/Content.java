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
        articles = articles;
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
        String out = "";
        for (Article article : this) {
            out += article;
        }
        return out;
    }

}
