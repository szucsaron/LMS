<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.codecool.web.model.Article" %>

<html lang="en">

<%
    String title = (String) request.getAttribute("title");
    String content = (String) request.getAttribute("content");
    String articleId = (String) request.getAttribute("articleId");

%>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CodeCool LMS</title>
    <link rel="stylesheet" href="content.css" href="https://fonts.googleapis.com/css?family=Crimson+Text|Work+Sans:400,700">
</head>
<body>
      <div class="login">
          <form action="edit_article" method="POST">
              <p><br>
                  <input type="text" name="title" placeholder="title" value="<%=title%>">
                  <br>
                  <br>
                  <textarea rows="12" cols="34" type="text" name="content" placeholder="content"><%=content%></textarea>
              </p>
              <input type="hidden" name="articleId" value=<%=articleId%>>
              <br><br>
              <input type="submit" value="SAVE">
              <br>
          </form>
      </div>
</body>
</html>
