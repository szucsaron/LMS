<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.codecool.web.model.User" %>

<html class="bg-1" lang="en">

<%
    // Java init
    User[] users = (User[]) request.getAttribute("users");
%>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CodeCool LMS</title>
    <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Comfortaa" />
    <link rel="stylesheet" href="index.css">
</head>

<body>
    <div class="wrapper">
        <h2>USERS</h2>
        <div class="scroll">
            <% for (User u : users) { %>
                <p><%= u.getRole() %> - <%= u.getUsername() %> - <%= u.getEmail() %></p>
            <% } %>
            <br><a class="button" href="content">BACK</a>
        </div>
    </div>
</body>

</html>
