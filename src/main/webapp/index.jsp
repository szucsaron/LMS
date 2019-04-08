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
        <div class="login">
            <h2>LOGIN</h2>
            <form action="login" method="POST">
                <p><br>
                <input type="text" name="username" placeholder="username">
                <br>
                <br>
                <input type="password" name="password" placeholder ="password"></p>
                <br>
                <jsp:include page="show-error.jsp"/>
                <input type="submit" value="LOGIN">
                <a class="button" href="register.jsp">REGISTER</a>
                <br>
            </form>
        </div>
    </div>
</body>

</html>
