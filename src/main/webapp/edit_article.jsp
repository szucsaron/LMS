<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.codecool.web.model.Article" %>

<html class="bg-1" lang="en">

<%
    String title = (String) request.getAttribute("title");
    String content = (String) request.getAttribute("content");
    String articleId = (String) request.getAttribute("articleId");

%>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CodeCool LMS</title>
    <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Comfortaa"/>
    <link rel="stylesheet" href="index.css">
</head>

<body>
    <div class="wrapper">
        <div class="textareas">
          <form action="edit_article" method="POST">
                  <input type="text" name="title" placeholder="title" value="<%=title%>">
                  <br>
                  <br>
                  <textarea type="text" name="content" placeholder="content"><%=content%></textarea>
              <input type="hidden" name="articleId" value=<%=articleId%>>
              <br><br>
              <input type="submit" value="SAVE">
              <a class="button" href="content">BACK</a>
              <br>
          </form>
        </div>
    </div>
</body>

</html>
