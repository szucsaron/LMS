<!doctype html>
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
    <link rel="stylesheet" href="index.css">
</head>
<body>
    <div class="leftbar">
    <form action="content" method="GET">
        <input type="text" name="search">
        <input type="submit" value="Search">
    </form>
        <%  Map<Integer, String> titles = (Map<Integer, String>) request.getAttribute("sidebar");
            int articleId = (int) request.getAttribute("articleId");
        %>
        <% for (Integer key : titles.keySet()) { %>
            <br><a href="content?pageID=<%=key%>"><%= titles.get(key) %></a>
        <% } %>

    </div>
    <div class="rightbar">
        <a class="button" href="users">Users</a>
        <a class="button" href="profile">Profile</a>
        <a class="button" href="add_article">Add Article</a>
        <%
        Article article = (Article) request.getAttribute("article");
        %>
        <p> <%=article.getTitle()%> </p>
        <p> <%=article.getText()%> </p>
        <a href="quiz?articleId=<%=articleId%>">
            Quiz
        </a>
    </div>



</body>
</html>
