<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.codecool.web.model.User" %>

<html lang="en">

<head>
    <meta charset="iso-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CodeCool LMS</title>
    <link rel="stylesheet" href="content.css" href="https://fonts.googleapis.com/css?family=Crimson+Text|Work+Sans:400,700">
</head>
<body>
    <script>
        var today = new Date();
        var dd = today.getDate();
        var mm = today.getMonth()+1; //January is 0!
        var yyyy = today.getFullYear();
        if(dd<10){
              dd='0'+dd
          }
          if(mm<10){
              mm='0'+mm
          }
        today = yyyy+'-'+mm+'-'+dd;
        document.getElementById("datefield").setAttribute("max", today);
    </script>

    <%
        // Java init
        User[] users = (User[]) request.getAttribute("users");
    %>

    <div class="users">
        <h2>Attendance</h2>

        <form action="attendance" method="POST">
            <input id="datefield" type='date' min='1899-01-01' max='2000-13-13'></input><br>
            <% for (User u : users) { %>
                <% if (u.getRole().equals("STUDENT")) { %>
                <td>
                <tr><%= u.getUsername() %></tr><tr><input type="checkbox" name="cucc" value="TRUE"></tr>
                <% } %>
            <% } %>
                </td>
            <br><input type="submit" value="Submit">
        </form>

    </div>
</body>
</html>
