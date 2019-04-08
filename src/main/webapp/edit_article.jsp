<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.codecool.web.model.Article" %>

<html class="bg-1" lang="en">

<%
    String title = (String) request.getAttribute("title");
    String content = (String) request.getAttribute("content");
    String articleId = (String) request.getAttribute("articleId");
    Integer level = (Integer) request.getAttribute("level");

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
        <div class="edit">
          <form action="edit_article" method="POST">
              <p><br>
                  <input type="text" name="title" placeholder="title" value="<%=title%>">
                  <br>
                  <br>
                  <textarea rows="8" cols="34" type="text" name="content" placeholder="content"><%=content%></textarea>
              </p>
              <br>
              <input type="number" name="level" min="1" max="100" value="<%=level%>">
              <input type="hidden" name="articleId" value="<%=articleId%>">
              <br><br>
              <input type="submit" value="SAVE">
              <br>
          </form>
        </div>
    </div>
</body>

</html>
