<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.codecool.web.model.Article" %>
<%@ page import="com.codecool.web.model.quiz.Quiz" %>
<%@ page import="com.codecool.web.model.quiz.Question" %>
<%@ page import="com.codecool.web.model.quiz.Answer" %>


<%
    // Java init
    Question question = (Question) request.getAttribute("question");
    int questionIndex = (int) request.getAttribute("questionIndex");
    int score = (int) request.getAttribute("score");
    int quizId = (int) request.getAttribute("quizId");
%>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CodeCool LMS</title>
   <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Comfortaa"/>
   <link rel="stylesheet" href="index.css"></head>
<body>

    <p>
        <%
                %>Score:<%=score%><%
                %><ul class="question"><h3><%=question.getDescription()%></h2><%
                    for (Answer answer : question) {
                        String parameters = "questionIndex=" + Integer.toString(questionIndex) + "&answerId=" + Integer.toString(answer.getId()) +
                                            "&quizId=" + quizId;
                        %>
                            <a class="answer" href="quiz?<%=parameters%>">
                                <%=answer.getText()%>
                            </a><br>
                        <%
                    }
                %></ul><%
        %>
    </p>



</body>
</html>
