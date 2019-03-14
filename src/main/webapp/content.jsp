<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.codecool.web.model.Article" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CodeCool LMS</title>
    <link rel="stylesheet" href="index.css" href="https://fonts.googleapis.com/css?family=Crimson+Text|Work+Sans:400,700">
</head>
<body>
    <div class="leftbar">
    <form action="content" method="GET">
        <input type="text" name="search">
        <input type="submit" value=" ">
    </form>
        <%  Map<Integer, String> titles = (Map<Integer, String>) request.getAttribute("sidebar");
            int articleId = (int) request.getAttribute("articleId");
        %>
        <% for (Integer key : titles.keySet()) { %>
            <br><a href="content?pageID=<%=key%>"><%= titles.get(key).toUpperCase() %><br></a>
        <% } %>

    </div>
    <div class="rightbar">
        <a class="button" href="users">USERS</a>
        <a class="button" href="profile">PROFILE</a>
        <a class="button" href="add_article">ARTICLE</a>
        <%
        Article article = (Article) request.getAttribute("article");
        %>
        <p> <%=article.getTitle()%> </p>
        <p> <%=article.getText()%> </p>
        <a href="quiz?articleId=<%=articleId%>">
            TAKE A QUIZ
        </a>
    </div>



</body>
</html>
