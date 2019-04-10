<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.codecool.web.model.Article" %>

<html class="bg-1" lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CodeCool LMS</title>
    <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Comfortaa"/>
    <link rel="stylesheet" href="index.css">
</head>

<body>
    <%
        // Java init
        String role = (String) request.getAttribute("role");
        Map<Integer, String> allQuiz = (Map<Integer, String>) request.getAttribute("quizzes");

        List<Integer> availableQuiz = (List<Integer>) request.getAttribute("available");
        List<Integer> committed = (List<Integer>) request.getAttribute("committed");
        List<Integer> passed = (List<Integer>) request.getAttribute("passed");

        Map<String, List<Integer>> toCheck = (Map<String, List<Integer>>) request.getAttribute("check");
    %>
    <div class="wrapper">
        <div class="quizzes">
            <h2>Quizzes</h2>
            <% if (role.equals("STUDENT")) { %>
                <p>To do: </p>
                      <% for (Integer id : allQuiz.keySet()) { %>
                          <% if (availableQuiz.contains(id)) { %>
                              <a href="quiz?quizId=<%=id%>"><%= allQuiz.get(id) %></a><br>
                          <% } %>
                      <% } %>
                <p>Waiting for evaluation:</p>
                    <% if (committed != null) { %>
                      <% for (Integer id : allQuiz.keySet()) { %>
                          <% if (committed.contains(id)) { %>
                              <%= allQuiz.get(id) %><br>
                          <% } %>
                      <% } %>
                    <% } %>
                <p>Successful assignments:</p>
                    <% if (passed != null) { %>
                      <% for (Integer id : allQuiz.keySet()) { %>
                          <% if (passed.contains(id)) { %>
                              <%= allQuiz.get(id) %><br>
                          <% } %>
                      <% } %>
                    <% } %>
            <% } else if (role.equals("MENTOR")) { %>
                <p>To check:</p>
                    <form action="evaluate" method="GET">
                    <% for (String student : toCheck.keySet()) { %>
                        <% if (toCheck.get(student).size() > 0) { %>
                            <i><%= student %></i><br>
                            <hr>
                            <% for (Integer id : toCheck.get(student)) { %>
                                <input type="hidden" value="<%= student %>" name="student">
                                <input type="hidden" value="<%= id %>" name="id">
                                <p><input type="submit" value="<%= allQuiz.get(id)%>"></p>
                            <% } %>
                            <hr>
                        <% } %>
                    <% } %>
                    </form>
            <% } %>
            <br><a class="button" href="content">BACK</a><br>
        </div>
    </div>
</body>

</html>
