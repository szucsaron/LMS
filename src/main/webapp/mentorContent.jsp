<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.codecool.web.model.Article" %>
<%@ page import="com.codecool.web.model.User" %>
<%@page import="java.util.*"%>

<%
    // Java Init
    Article article = (Article) request.getAttribute("article");
    Map<Integer, String> titles = (Map<Integer, String>) request.getAttribute("sidebar");
    int articleId = (int) request.getAttribute("articleId");
    Integer quizId = (Integer) article.getQuizId();
%>

<html class="bg-2" lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CodeCool LMS</title>
    <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Comfortaa"/>
    <link rel="stylesheet" href="index.css"/>
</head>

<body>
    <nav>
        <a class="button" href="users">USERS</a>
        <a class="button" href="profile">EDIT PROFILE</a>
        <a class="button" href="edit_article?articleId=<%=article.getId()%>">EDIT ARTICLE</a>
        <a class="button" href="edit_article?articleId=new">ADD ARTICLE</a>
        <a class="button" href="quizlist">ASSIGNMENTS</a>
        <a class="button" href="attendance">ATTENDANCE</a>
        <a class="button" href="quiz_edit?quizId=<%=quizId%>&pageId=<%=article.getId()%>">EDIT QUIZ</a>
    </nav>
    <div class="leftbar">
    <form action="content" method="GET">
        <input type="text" name="search">
        <input type="submit" value=" ">
        <a class="button" href="logout"</a>
    </form>
        <% for (Integer key : titles.keySet()) { %>
            <br><a href="content?pageID=<%=key%>"><%= titles.get(key).toUpperCase() %><br></a>
        <% } %>

    </div>
    <div class="rightbar">
        <div class="content">
            <h2>
                <p> <%=article.getTitle()%> <br><br>
                <%=article.getText()%> </p>
            </h2>
        </div>
    </div>
</body>

</html>
