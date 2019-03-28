<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.codecool.web.model.Article" %>

<html lang="en">

<%

%>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CodeCool LMS</title>
    <link rel="stylesheet" href="content.css" href="https://fonts.googleapis.com/css?family=Crimson+Text|Work+Sans:400,700">
</head>
<body>
<form action="edit_article" method="POST">
        <p><br>
        <input type="text" name="article_title" placeholder="title">
        <br>
        <br>
        <input type="text" name="content" placeholder="content"></p>
        <br><br>
        <input type="submit" value="SAVE">
        <br>
    </form>
</body>
</html>
