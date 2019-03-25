<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.codecool.web.model.User" %>

<%
    // Java init
    User user = (User) request.getAttribute("user");
%>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CodeCool LMS</title>
    <link rel="stylesheet" href="content.css" href="https://fonts.googleapis.com/css?family=Crimson+Text|Work+Sans:400,700">
</head>
<body>

    <div class="login">
        <h2>PROFILE</h2>
        <form action="profile" method="POST">
            <p>Username:<br>
            <input type="text" name="username" value="<%= user.getUsername()%>">
            <br>
            Password:<br>
            <input type="password" name="password" value="<%= user.getPassword()%>">
            <br>
            Your e-mail address:<br>
            <input readonly type="text" name="email" placeholder="<%= user.getEmail()%>"></p>
            <br>
            <input type="submit" value="SAVE">
        </form>
    </div>
</body>
</html>
