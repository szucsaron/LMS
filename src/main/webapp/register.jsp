<!DOCTYPE html>
<html class="bg-1" lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CodeCool LMS</title>
    <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Comfortaa"/>
    <link rel="stylesheet" href="index.css">
</head>

<body>
<div class="wrapper">
    <div class="register">
        <h2>REGISTER</h2>
        <form action="register" method="POST">
            <p>
                <input type="text" name="username" placeholder="username">
                <br>
                <br>
                <input type="password" name="password" placeholder="password">
                <br>
                <br>
                <input type="text" name="email" placeholder="e-mail address"></p>
                <p><select name="role">
                  <option value="STUDENT">Student</option>
                  <option value="MENTOR">Mentor</option>
                </select></p>
            <jsp:include page="show-error.jsp"/>
            <input type="submit" value="REGISTER">
            <a class="button" href="index.jsp">LOGIN</a>
        </form>
    </div>
</div>
</body>

</html>
