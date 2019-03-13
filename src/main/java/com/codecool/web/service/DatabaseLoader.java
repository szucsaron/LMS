package com.codecool.web.service;

import com.codecool.web.model.Article;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseLoader {

    private Document getDocumentFromFile(String filepath) throws IOException {
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(new File(filepath));
            return document;
        } catch (ParserConfigurationException | SAXException e) {
            throw new IOException();
        }
    }

    public List<Article> loadArticles(String filepath) throws IOException{

        Document doc = getDocumentFromFile(filepath);

        return new ArrayList<>();

    }

}
