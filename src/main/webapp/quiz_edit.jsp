<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.codecool.web.model.User" %>
<%@ page import="java.util.Date" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html class="bg-1" lang="en">

<head>
    <meta charset="iso-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CodeCool LMS</title>
    <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Comfortaa"/>
    <link rel="stylesheet" href="index.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>

<body>
    <form>
         Question:<br>
         <input type="text" name="question" value="${question.getDescription()}"><br>
         Answers:<br>
         <input type="text" name="ans0"><br>
             <input type="radio" name="correct" value="0">
         <input type="text" name="ans1"><br>
             <input type="radio" name="correct" value="1">
         <input type="text" name="ans2"><br>
             <input type="radio" name="correct" value="2">
         <input type="text" name="ans3"><br>
             <input type="radio" name="correct" value="3">
        <input type="submit" value="SUBMIT"><br>
        <input type="hidden" name="quizId" value="${quizId}">
        <input type="hidden" name="questionId" value="${questionIndex}">
    </form>

</body>

</html>
