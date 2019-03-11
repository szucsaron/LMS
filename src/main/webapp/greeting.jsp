<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.codecool.web.model.Greeting" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Greeting</title>
</head>
<body>
<h1>Greeting!</h1>
<% List<Greeting> greetings = (List<Greeting>) request.getAttribute("greetings"); %>
<% for (Greeting g : greetings) { %>
    <p><%= g.getLang() %>: <%= g.getText() %></p>
<% } %>
<a href="index.html">Go back to the <em>index</em> page.</a>
<br>
<a href="greeting?jstl=true">Click here to list the <em>greetings</em> using <strong>JSTL</strong>!</a>
</body>
</html>
