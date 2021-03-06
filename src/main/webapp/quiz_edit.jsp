<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.codecool.web.model.User" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.codecool.web.model.quiz.Answer" %>
<%@ page import="com.codecool.web.model.quiz.Question" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html class="bg-1" lang="en">

<head>
    <meta charset="iso-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CodeCool LMS</title>
    <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Comfortaa"/>
    <link rel="stylesheet" href="index.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>

<body>
<div class = "rightbar">
<div class = "content">
    <%
        Question question = (Question) request.getAttribute("question");
        List<Answer> answers = new ArrayList();
        for (Answer answer : question) {
            answers.add(answer);
        }
    %>
    <form method="post">
         Question # ${questionIndex + 1}:<br>
         <input type="text"  style="width:70%;" name="questionText" value="${question.getDescription()}"><br>
         Answers:
         <p>
         <% for (Answer answer : question) { %>
             <input type="text" style="width:70%;" name="ans<%=answer.getId()%>" value="<%=answer.getText()%>">
             <input type="radio" name="correct" value="<%=answer.getId()%>"

             <% if (answer.validate()) { %>
                 checked
             <%}%>

             ><br>
         <% } %>
        </p>

        <input type="hidden" name="quizId" value="${quizId}">
        <input type="hidden" name="questionIndex" value="${questionIndex}">
        <input type="hidden" name="questionId" value="${question.getId()}">
        <input type="hidden" name="pageId" value="${pageId}">
        <p>
            <input type="submit" name="submit" value="NEXT" class="button">
            <input type="submit" name="submit" value="PREVIOUS" class="button">
        </p>
        <p>
            <input type="submit" name="submit" value="SAVE" class="button">
            <input type="submit" name="submit" value="NEW QUESTION" class="button">
            <input type="submit" name="submit" value="DELETE QUESTION" class="button">
        </p>
        <p>
            <input type="submit" name="submit" value="BACK TO ARTICLE" class="button">
        </p>
    </form>
</div>
</div>
</body>

</html>
