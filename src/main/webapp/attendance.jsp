<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.codecool.web.model.User" %>

<html lang="en">

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
    <%
        // Java init
        User[] users = (User[]) request.getAttribute("users");
    %>
    <script>
         $(function() {
            $('#datepicker').datepicker().datepicker('setDate', new Date());
            $('#datepicker').datepicker( "option", "maxDate", new Date());
            $('#datepicker').attr("autocomplete", "off");
         });
    </script>

    <div class="users">
        <h2>Attendance</h2>

        <form class="attendance" action="attendance" method="POST">
            <input type="text" id="datepicker" name="date" ></input><br><br>
            <% for (User u : users) { %>
                <% if (u.getRole().equals("STUDENT")) { %>
                <td>
                <tr><%= u.getUsername() %></tr><tr><input type="checkbox" name="<%= u.getUsername() %>" value="TRUE"></tr><br>
                <% } %>
            <% } %>
                </td>
            <br><br>
            <input type="submit" value="Submit">
        </form>

    </div>
</body>
</html>
