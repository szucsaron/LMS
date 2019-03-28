<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.codecool.web.model.Article" %>

<%
    // Java init
    boolean success = (boolean) request.getAttribute("result");
%>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CodeCool LMS</title>
    <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Comfortaa"/>
    <link rel="stylesheet" href="index.css">
</head>

<body>
    <p>
        <%
            if (success) {
                out.println("Quiz successfully completed");
            } else {
                out.println("Quiz failed.");
            }
        %>
    </p>
    <a href="content">Return</a>
</body>
