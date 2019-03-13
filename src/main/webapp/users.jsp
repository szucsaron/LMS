<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.codecool.web.model.User" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CodeCool LMS</title>
    <link rel="stylesheet" href="index.css" href="https://fonts.googleapis.com/css?family=Crimson+Text|Work+Sans:400,700">
</head>
<body>
    <div class="users">
        <h2>Users</h2>
        <% User[] users = (User[]) request.getAttribute("users"); %>
        <% for (User u : users) { %>
            <p><%= u.getUsername() %> - <%= u.getEmail() %></p>
        <% } %>
    </div>
</body>
</html>
