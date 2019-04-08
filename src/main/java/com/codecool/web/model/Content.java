package com.codecool.web.model;

import com.codecool.web.model.quiz.Question;
import com.codecool.web.model.quiz.Quiz;

import java.util.*;

public class Content {
    private Map<Integer, Quiz> quizzes;
    private Map<Integer, Quiz> questionContainerQuiz = new HashMap<>();
    private Map<Integer, Question> questions;
    private Map<Integer, Article> articles;


    public Content(Map<Integer, Quiz> quizzes, List<Article> articles) {
        this.quizzes = quizzes;

        for (Integer quizId : quizzes.keySet()) {
            Quiz old = quizzes.get(quizId);
            Quiz container = new Quiz(old.getId(), null, 0);
            for (Question question : old) {
                container.addQuestion(question);
            }
            questionContainerQuiz.put(quizId, container);
            old.removeQuestions();
        }

        this.articles = new HashMap<>();
        for (Article article : articles) {
            this.articles.put(article.getId(), article);
        }

    }

    public Map<Integer, Quiz> getQuizzes() {
        return quizzes;
    }

    public Map<Integer, Question> getQuestions() {
        return questions;
    }

    public Map<Integer, Article> getArticles() {
        return articles;
    }

    public Question getQuestionsByQuizIndex(int quizId, int index) {
        return questionContainerQuiz.get(quizId).getQuestion(index);
    }

}
