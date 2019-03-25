<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.codecool.web.model.Article" %>

<html lang="en">

<%
    // Java init
    HashMap<Integer, String> quiz = (HashMap<Integer, String>) request.getAttribute("quizes");
%>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CodeCool LMS</title>
    <link rel="stylesheet" href="content.css" href="https://fonts.googleapis.com/css?family=Crimson+Text|Work+Sans:400,700">
</head>
<body>
    <div class="users">
        <h2>Quizes</h2>
        <% for (Integer id : quiz.keySet()) { %>
            <a href="quiz?id=<%=id%>"><%= quiz.get(id) %></a>
        <% } %>
    </div>
</body>
</html>
