package com.codecool.web.service.database;

import com.codecool.web.model.Article;
import com.codecool.web.model.Content;
import com.codecool.web.model.quiz.Answer;
import com.codecool.web.model.quiz.Question;
import com.codecool.web.model.quiz.Quiz;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SqlGenerator {
    private Content content;

    public SqlGenerator(Content content) {
        this.content = content;
    }

    public String generate() throws IOException {
        StringBuilder sb = new StringBuilder();
        StringBuilder answerSb = new StringBuilder();
        createTables(sb);
        getQuizzes(sb);
        getQuestions(sb, answerSb);
        sb.append(answerSb);
        getArticles(sb);

        String output = sb.toString();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("goat_init.sql")));
        bufferedWriter.write(output);
        bufferedWriter.close();
        return output;
    }

    private void getQuizzes(StringBuilder sb) {
        Map<Integer, Quiz> quizzes = content.getQuizzes();
        sb.append("INSERT INTO quizzes VALUES ");
        int i = 0;
        for (Quiz quiz : quizzes.values()) {
            String id = Integer.toString(quiz.getId());
            String title = quiz.getDescription();
            sb.append("(").append(id).append(",'").append(title).append("'");
            sb.append(")");
            if (i < quizzes.size() - 1) {
                sb.append(",");
            }
            sb.append("\n");
            i++;
        }

        sb.replace(sb.length() - 1, sb.length(), "");

        sb.append(";\n\n");
    }

    private void getQuestions(StringBuilder sb, StringBuilder answerSb) {
        Map<Integer, Question> questions = content.getQuestions();
        Set<Integer> quizIds = content.getQuizzes().keySet();
        sb.append("INSERT INTO questions (id, quiz_id, title) VALUES\n");
        answerSb.append("INSERT INTO answers (id, question_id, answer, correct) VALUES\n");

        for (int quizId : quizIds) {
            int questionIndex = 0;
            while (true) {
                try {
                    Question question = content.getQuestionsByQuizIndex(quizId, questionIndex);
                    sb.append("(").append(question.getId()).append(",")
                        .append(quizId).append(",")
                        .append("'").append(question.getDescription().replace("'", "")).append("'),\n");
                    getAnswer(question, answerSb);
                } catch (IndexOutOfBoundsException e) {
                    break;
                }
                questionIndex++;
            }
        }

        sb.replace(sb.length() - 2, sb.length(), "");
        sb.append(";\n\n");

        answerSb.replace(answerSb.length() - 2, answerSb.length(), "");
        answerSb.append(";\n\n");

    }

    private void getAnswer(Question question, StringBuilder sb) {
        String questionId = Integer.toString(question.getId());
        String c = ", ";
        for (Answer answer : question) {
            String id = Integer.toString(answer.getId());

            String title = answer.getText().replace("'", "");
            String correct;
            if (answer.validate()) {
                correct = "1";
            } else {
                correct = "0";
            }
            sb.append("(").append(id).append(c).append(questionId).append(c)
                .append("'").append(title).append("',")
                .append("'").append(correct).append("'),\n");
        }
    }

    private void getArticles(StringBuilder sb) {
        Map<Integer, Article> articles = content.getArticles();
        Collection<Integer> articleIds = articles.keySet();
        sb.append("INSERT INTO articles (id, quiz_id, title, textcontent) VALUES\n");
        for (int id : articleIds) {
            Article article = articles.get(id);
            sb.append(String.format("(%d, %d, '%s', '%s'),\n",
                                    article.getId(),
                                    article.getQuizId(),
                                    article.getTitle(),
                                    article.getText().replace("'", "").replace("\n", "")));
        }
        sb.replace(sb.length() - 2, sb.length(), "");
        sb.append(";\n\n");

    }

    private void createTables(StringBuilder sb) {
        String cr = "DROP TABLE IF EXISTS solutions;\n" +
            "DROP TABLE IF EXISTS answers;\n" +
            "DROP TABLE IF EXISTS articles;\n" +
            "DROP TABLE IF EXISTS questions;\n" +
            "DROP TABLE IF EXISTS users;\n" +
            "DROP TABLE IF EXISTS quizzes;\n" +
            "\n" +
            "\n" +
            "CREATE TABLE users (\n" +
            "\tid INT primary key,\n" +
            "\tuser_name VARCHAR(50),\n" +
            "\tpasswd VARCHAR(50)\n" +
            ");\n" +
            "\n" +
            "CREATE TABLE quizzes (\n" +
            "\tid INT primary key,\n" +
            "\ttitle VARCHAR(200)\n" +
            ");\n" +
            "\n" +
            "CREATE TABLE questions (\n" +
            "\tid serial primary key,\n" +
            "\tquiz_id INT references quizzes(id),\n" +
            "\ttitle VARCHAR(200)\n" +
            ");\n" +
            "\n" +
            "CREATE TABLE articles (\n" +
            "\tid INT primary key,\n" +
            "\tquiz_id INT references quizzes(id),\n" +
            "\ttitle VARCHAR(200),\n" +
            "\ttextcontent text\n" +
            ");\n" +
            "\n" +
            "CREATE TABLE answers (\n" +
            "\tid serial primary key,\n" +
            "\tquestion_id INT references questions(id),\n" +
            "\tanswer VARCHAR(200),\n" +
            "\tcorrect bit\n" +
            ");\n" +
            "\n" +
            "CREATE TABLE solutions (\n" +
            "\tuser_id INT references users(id),\n" +
            "\tanswer_id INT references answers(id),\n" +
            "\tprimary key(user_id, answer_id)\n" +
            ");\n\n";

        sb.append(cr);
    }


}
