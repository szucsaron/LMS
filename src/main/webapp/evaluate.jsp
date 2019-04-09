<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.codecool.web.model.Solution" %>
<%@ page import="com.codecool.web.model.User" %>

<%
    // Java init
    User user = (User) request.getAttribute("user");
    List<Solution> solutions = (List<Solution>) request.getAttribute("solutions");
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
            <h3><%= user.getUsername() %><h3>
            <p><%= solutions.get(0).getTitle() %></p>
            <% for (Solution s : solutions) { %>
                <p><%=s.getQuestion() %></p>
                <p><%=s.getAnswer() %></p>
            <% } %>
            <form action="evaluate" method="POST">
                <input type="submit" value="passed" name="OK">
                <input type="submit" value="failed" name="RETRY">
            </form>
        </div>
    </div>
</body>

</html>
