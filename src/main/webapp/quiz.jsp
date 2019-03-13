<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.codecool.web.model.Article" %>
<%@ page import="com.codecool.web.model.quiz.Quiz" %>
<%@ page import="com.codecool.web.model.quiz.Question" %>



<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CodeCool LMS</title>
    <link rel="stylesheet" href="index.css" href="https://fonts.googleapis.com/css?family=Crimson+Text|Work+Sans:400,700">
</head>
<body>

    <p>
        <%
            Question question = (Question) request.getAttribute("question");
            int questionId = (int) request.getAttribute("questionId");
            int score = (int) request.getAttribute("score");
            int articleId = (int) request.getAttribute("articleId");

            int quizId = 0;
                %>Score:<%=score%><%
                %><ul class="question"><h3><%=question.getDescription()%></h2><%
                    int answerId = 0;
                    for (String answer : question) {
                        String parameters = "questionId=" + Integer.toString(questionId) + "&answerId=" + Integer.toString(answerId) +
                                            "&articleId=" + articleId;
                        %>
                            <a class="answer" href="quiz?<%=parameters%>">
                                <%=answer%>
                            </a><br>
                        <%
                        answerId++;
                    }
                    quizId++;
                %></ul><%

        %>
    </p>



</body>
</html>
