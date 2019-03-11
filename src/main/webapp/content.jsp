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
        <input type="text" placeholder="Search...">
        <% Map<Integer, String> titles = (Map<Integer, String>) request.getAttribute("sidebar"); %>
        <% for (Integer key : titles.keySet()) { %>
            <a href="content?pageID=<%=key%>"><%= titles.get(key) %></a>
        <% } %>

    </div>
    <div class="rightbar">
        <%
        Article article = (Article) request.getAttribute("article");
        %>
        <p> <%=article.getTitle()%> </p>
        </p> <%=article.getText()%> </p>
    </div>



</body>
</html>
