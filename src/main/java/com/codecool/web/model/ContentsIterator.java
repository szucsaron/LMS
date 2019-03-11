package com.codecool.web.model;

import java.util.Iterator;

public class ContentsIterator implements Iterator<Article> {
    private Content content;
    private int index;

    public ContentsIterator(Content content) {
        this.content = content;
    }

    public boolean hasNext() {
        return !(index >= content.size());
    }

    public Article next() {
        Article article = content.getArticle(index);
        index++;
        return article;
    }
}
