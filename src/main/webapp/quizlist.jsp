<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.codecool.web.model.Article" %>

<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CodeCool LMS</title>
    <link rel="stylesheet" href="content.css" href="https://fonts.googleapis.com/css?family=Crimson+Text|Work+Sans:400,700">
</head>
<body>
    <%
        // Java init
        Map<Integer, String> allQuiz = (Map<Integer, String>) request.getAttribute("quizes");
        List<Integer> avaiableQuiz = (List<Integer>) request.getAttribute("avaiable");
    %>

    <div class="users">
        <h2>Quizes</h2>
        <ul>
            <% for (Integer id : allQuiz.keySet()) { %>
                <% if (avaiableQuiz.contains(id)) { %>
                    <li><a href="quiz?articleId=<%=id%>"><%= allQuiz.get(id) %></a></li>
                <% } %>
            <% } %>
        </ul>
    </div>
</body>
</html>
