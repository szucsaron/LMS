<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Greeting</title>
</head>
<body>
<h1>Greeting!</h1>
<%
String message = null;
String value = null;
Cookie[] cookies = request.getCookies();
if(cookies != null){
    for(Cookie cookie : cookies){
        %>
            <p><%=cookie.getName()%></p>
            <p><%=cookie.getValue()%></p>

        <%

    }
}
%>
<form action="greeting" method="post">

    Username: <input type="text" name="username">
    <br>
    Password: <input type="password" name="pwd">
    <br><br>
    <input type="submit" value="Login">
</form>

<a href="index.html">Go back to the <em>index</em> page.</a>
<br>
<a href="greeting?jstl=true">Click here to list the <em>greetings</em> using <strong>JSTL</strong>!</a>
</body>
</html>
