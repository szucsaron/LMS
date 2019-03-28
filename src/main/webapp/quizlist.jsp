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
    <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Comfortaa"/>
    <link rel="stylesheet" href="index.css"></head>
<body>
    <%
        // Java init
        String role = (String) request.getAttribute("role");
        Map<Integer, String> allQuiz = (Map<Integer, String>) request.getAttribute("quizes");

          List<Integer> availableQuiz = (List<Integer>) request.getAttribute("available");
          List<Integer> committed = (List<Integer>) request.getAttribute("committed");
          List<Integer> passed = (List<Integer>) request.getAttribute("passed");

          Map<String, List<Integer>> toCheck = (Map<String, List<Integer>>) request.getAttribute("check");


    %>

    <div class="users">
        <h2>Quizzes</h2>
        <% if (role.equals("STUDENT")) { %>
            <p>To do: </p>
              <ul>
                  <% for (Integer id : allQuiz.keySet()) { %>
                      <% if (availableQuiz.contains(id)) { %>
                          <li><a href="quiz?quizId=<%=id%>"><%= allQuiz.get(id) %></a></li>
                      <% } %>
                  <% } %>
              </ul>
            <p>Waiting for evaluation:</p>
              <ul>
                  <% for (Integer id : allQuiz.keySet()) { %>
                      <% if (committed.contains(id)) { %>
                          <li><%= allQuiz.get(id) %></li>
                      <% } %>
                  <% } %>
              </ul>
            <p>Successful assignments:</p>
              <ul>
                  <% for (Integer id : allQuiz.keySet()) { %>
                      <% if (passed.contains(id)) { %>
                          <li><%= allQuiz.get(id) %></li>
                      <% } %>
                  <% } %>
              </ul>
        <% } else if (role.equals("MENTOR")) { %>
            <p>To check:</p>
            <ul>
                <% for (String student : toCheck.keySet()) { %>
                    <i><%= student %></i><br>
                    <% for (Integer id : toCheck.get(student)) { %>
                        <li><a href="quiz?quizId=<%=id%>"><%= allQuiz.get(id) %></a></li>
                    <% } %>
                <% } %>
            </ul>
        <% } %>
    </div>
</body>
</html>
