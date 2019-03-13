package com.codecool.web.service;

import com.codecool.web.model.Article;
import com.codecool.web.model.Content;
import com.codecool.web.model.quiz.Question;
import com.codecool.web.model.quiz.Quiz;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Content loadContent(String articleFilePath, String quizFilePath) throws IOException {
        Map<Integer, Article> articles = getArticles(getDocumentFromFile(articleFilePath));
        Map<Integer, Quiz> quizzes = getQuizzes(getDocumentFromFile(quizFilePath));

        for (int quizId: quizzes.keySet()) {
            Article article = articles.get(quizId);
            if (article != null) {
                article.addQuiz(quizzes.get(quizId));
            }
        }

        List<Article> c = new ArrayList<>();
        for (Article article : articles.values()) {
            c.add(article);
        }


        Content content = new Content(c);


        return content;

    }

    private Map<Integer, Article> getArticles(Document doc) {
        NodeList docArticles = doc.getElementsByTagName("article");
        int length = docArticles.getLength();
        Map<Integer, Article> articles = new HashMap<>();
        for (int i = 0; i < length; i++) {
            Element docArticle = (Element) docArticles.item(i);
            int id = Integer.parseInt(docArticle.getAttribute("id"));
            String title = docArticle.getAttribute("title");
            String text = docArticle.getTextContent();
            text = text.replace("\n", " ");
            text = text.replace("    ", "");

            Article article = new Article(title, text);
            articles.put(id, article);
        }
        return articles;
    }

    private Map<Integer, Quiz> getQuizzes(Document doc) {
        NodeList docArticles = doc.getElementsByTagName("quiz");
        int length = docArticles.getLength();
        Map<Integer, Quiz> quizzes = new HashMap<>();
        for (int quizI = 0; quizI < length; quizI++) {
            Element docQuiz = (Element) docArticles.item(quizI);
            int quizId = Integer.parseInt(docQuiz.getAttribute("id"));
            String quizTitle = docQuiz.getAttribute("title");
            NodeList questions = docQuiz.getElementsByTagName("question");

            Quiz quiz = new Quiz(quizTitle);
            for (int questI = 0; questI < questions.getLength(); questI++) {
                Element docQuestion = (Element) questions.item(questI);
                quiz.addQuestion(createQuestion(docQuestion));
            }

            quizzes.put(quizId, quiz);
        }
        return quizzes;
    }

    private Question createQuestion(Element docQuestion) {
        String text = docQuestion.getAttribute("title");
        NodeList docAnswers = docQuestion.getElementsByTagName("answer");
        int correct = Integer.parseInt(docQuestion.getAttribute("correct"));

        Question question = new Question(text);
        question.setAsCorrect(correct);
        int length = docAnswers.getLength();
        for (int i = 0; i < length; i++) {
            Element docAnswer = (Element) docAnswers.item(i);
            String answer = docAnswer.getTextContent();
            question.addAnswer(answer);
        }
        return question;
    }

}
