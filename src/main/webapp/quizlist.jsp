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
            <h3>To do: </h3>
                      <% for (Integer id : allQuiz.keySet()) { %>
                          <% if (availableQuiz.contains(id)) { %>
                              <a href="quiz?quizId=<%=id%>"><%= allQuiz.get(id) %></a><br>
                          <% } %>
                      <% } %>
                <hr>
                    <% if (committed != null) { %>
                    <h3>Waiting for evaluation:</h3>
                      <% for (Integer id : allQuiz.keySet()) { %>
                          <% if (committed.contains(id)) { %>
                              <%= allQuiz.get(id) %><br>
                          <% } %>
                      <% } %>
                      <hr>
                    <% } %>
                    <% if (passed != null) { %>
                    <h3>Successful assignments:</h3>
                      <% for (Integer id : allQuiz.keySet()) { %>
                          <% if (passed.contains(id)) { %>
                              <%= allQuiz.get(id) %><br>
                          <% } %>
                      <% } %>
                    <% } %>
            <% } else if (role.equals("MENTOR")) { %>
                <h3>To check:</h3>
                    <% for (String student : toCheck.keySet()) { %>
                        <% if (toCheck.get(student).size() > 0) { %>
                            <i><%= student %></i><br>
                            <hr>
                            <li>
                            <% for (Integer id : toCheck.get(student)) { %>
                                  <a href="evaluate?student=<%= student%>&id=<%= id %>"> <%= allQuiz.get(id) %> </a><br>
                            <% } %>
                            </li>
                            <hr>
                        <% } %>
                    <% } %>
            <% } %>
            <br><a class="button" href="content">BACK</a><br>
        </div>
    </div>
</body>

</html>
