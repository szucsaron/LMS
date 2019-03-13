<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.codecool.web.model.Article" %>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CodeCool LMS</title>
    <link rel="stylesheet" href="index.css" href="https://fonts.googleapis.com/css?family=Crimson+Text|Work+Sans:400,700">
</head>

<%
boolean success = (boolean) request.getAttribute("result");
%>


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
