<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="login.css">
    <title>Login</title>
</head>
<body>
    <div>
        <h1>Welcome</h1>
        <h1>Log in to access your account</h1>
    </div>
    <form action="Authentification" method="get">
        <input type="text" name="login" id="login" placeholder="Login">
        <input type="password" name="password" id="password" placeholder="Password">
        <button>Log in</button>
    </form>
</body>
</html>