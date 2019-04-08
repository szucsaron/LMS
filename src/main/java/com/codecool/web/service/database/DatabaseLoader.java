package com.codecool.web.service.database;

import com.codecool.web.model.Article;
import com.codecool.web.model.Content;
import com.codecool.web.model.quiz.Answer;
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

    private int dbQuestionId = 0;
    private int dbAnswerId = 0;

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

        for (int quizId : quizzes.keySet()) {
            Article article = articles.get(quizId);
            if (article != null) {
                article.addQuiz(quizzes.get(quizId));
            }
        }

        List<Article> articleList = new ArrayList<>();
        for (Article article : articles.values()) {
            articleList.add(article);
        }



        Content content = new Content(quizzes, articleList);


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
            int level = Integer.parseInt(docArticle.getAttribute("level"));
            int score = Integer.parseInt(docArticle.getAttribute("lvlIncrease"));


            String text = docArticle.getTextContent();
            text = text.replace("%%p", "<p>");
            text = text.replace("%!p", "</p>");
            text = text.replace("%%br", "<br>");


            text = text.replace("    ", "");

            Article article = new Article(id, title, text, null, level);
            article.setLvlIncrease(score);
            articles.put(id, article);
        }
        return articles;
    }

    private Map<Integer, Quiz> getQuizzes(Document doc) {
        NodeList docArticles = doc.getElementsByTagName("quiz");
        int length = docArticles.getLength();
        Map<Integer, Quiz> quizzes = new HashMap<>();
        for (int dbQuizId = 0; dbQuizId < length; dbQuizId++) {
            Element docQuiz = (Element) docArticles.item(dbQuizId);
            int quizId = Integer.parseInt(docQuiz.getAttribute("id"));
            String quizTitle = docQuiz.getAttribute("title");
            NodeList questions = docQuiz.getElementsByTagName("question");

            Quiz quiz = new Quiz(quizId, quizTitle);
            for (int questI = 0; questI < questions.getLength(); questI++) {
                Element docQuestion = (Element) questions.item(questI);
                quiz.addQuestion(createQuestion(docQuestion));
                dbQuestionId++;
            }

            quizzes.put(quizId, quiz);
        }
        return quizzes;
    }

    private Question createQuestion(Element docQuestion) {
        String text = docQuestion.getAttribute("title");
        NodeList docAnswers = docQuestion.getElementsByTagName("answer");
        int correct = Integer.parseInt(docQuestion.getAttribute("correct")) + dbAnswerId;

        Question question = new Question(text, dbQuestionId);
        int length = docAnswers.getLength();
        for (int i = 0; i < length; i++) {
            Element docAnswer = (Element) docAnswers.item(i);
            String answer = docAnswer.getTextContent();
            boolean isCorrect = correct == dbAnswerId;
            question.addAnswer(new Answer(dbAnswerId, answer, isCorrect));
            dbAnswerId++;
        }
        return question;
    }

}
