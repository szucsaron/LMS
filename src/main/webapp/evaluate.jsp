<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.codecool.web.model.quiz.Solution" %>
<%@ page import="com.codecool.web.model.User" %>
<%@ page import="com.codecool.web.model.quiz.Question" %>
<%@ page import="com.codecool.web.model.quiz.Answer" %>
<%@ page import="com.codecool.web.model.quiz.Quiz" %>

<%
    // Java init
    String studentName = (String) request.getAttribute("student");
    Solution solution = (Solution) request.getAttribute("solution");
%>

<html class="bg-1" lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CodeCool LMS</title>
    <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Comfortaa"/>
    <link rel="stylesheet" href="index.css">
</head>

<body>
    <div class="wrapper">
        <div class="login">
            <h3><%= studentName %><h3>
            <p><%= solution.getDescription() %></p>
            <% for (Question q : solution) { %>
                <p><%= q.toString() %></p>
            <% } %>
            <form action="evaluate" method="POST">
                <input type="submit" value="PASSED" name="OK">
                <input type="submit" value="FAILED" name="RETRY">
            </form>
        </div>
    </div>
</body>

</html>
