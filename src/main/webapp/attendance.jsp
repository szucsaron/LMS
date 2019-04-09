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

    <script>
         $(function() {
            $('#datepicker').datepicker().datepicker('setDate', new Date());
            $('#datepicker').datepicker( "option", "maxDate", new Date());
            $('#datepicker').attr("autocomplete", "off");
         });
    </script>

    <div class="wrapper">
        <h2>Attendance</h2>
        <div class="scroll">
            <form class="attendance" action="attendance" method="POST">
                <input type="text" id="datepicker" name="date" ></input><br><br>
                <c:forEach var="u" items="${users}">
                    <c:set var="chosen" scope="session" value="${chosen}"/>
                    <c:choose>
                        <c:when test="${u.getRole() == 'STUDENT'}">
                            <c:choose>
                                <c:when test="${u.getAttendance(chosen)}">
                                    ${u.getUsername()} <input type="checkbox" name="${u.getUsername()}" value="TRUE" checked><br>
                                </c:when>
                                <c:otherwise>
                                    ${u.getUsername()} <input type="checkbox" name="${u.getUsername()}" value="TRUE"><br>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                    </c:choose>
                </c:forEach>
                <br><br>
                <input type="submit" value="SUBMIT">
                <a class="button" href="content">BACK</a>
            </form>
        </div>
    </div>
</body>

</html>
