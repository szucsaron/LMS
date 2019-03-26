package com.codecool.web.service;

import com.codecool.web.model.Article;
import com.codecool.web.model.Content;
import com.codecool.web.model.NoSuchUserException;

import com.codecool.web.model.User;

import java.io.IOException;
import java.util.*;

public interface Database {
    public void setLocation(String locationPrefix);

    public Article getArticle(int id);

    public Map<Integer, String> getArticleIds();

    public void addArticle(Article article);

    public Map<Integer, String> getArticleIdsBySearch(String toFind) ;

    public void addUser(User user) ;

    public User getUserByName(String userName) throws NoSuchUserException ;

    public User[] getUsersArray() ;

    public Set<String> getUserNames() ;


}
