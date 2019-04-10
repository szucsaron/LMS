<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.codecool.web.model.quiz.Solution" %>
<%@ page import="com.codecool.web.model.User" %>

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
            <% while (solution.iterator().hasNext()) { %>
                <p><%=solution.iterator().next() %></p>
            <% } %>
            <form action="evaluate" method="POST">
                <input type="submit" value="PASSED" name="OK">
                <input type="submit" value="FAILED" name="RETRY">
            </form>
        </div>
    </div>
</body>

</html>
