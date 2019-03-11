<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Greeting</title>
</head>
<body>
<h1>Greeting!</h1>
<c:forEach var="g" items="${greetings}">
    <p><c:out value="${g.lang}"/>: <c:out value="${g.text}"/><p>
</c:forEach>
<a href="index.html">Go back to the <em>index</em> page.</a>
<br>
<a href="greeting">Click here to list the <em>greetings</em> using <strong>JSP</strong>!</a
</body>
</html>
