<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h1>Hello! Please provide your user details:</h1>
<h4 style="color: red">${message}</h4>
<form method="post" action="${pageContext.request.contextPath}/registration">
    <div>
        <label for="name">Name:</label>
        <input type="text" name="name" id="name">
    </div>
    <div style="margin-top: 15px">
        <label for="login">Login:</label>
        <input type="text" name="login" id="login">
    </div>
    <div style="margin-top: 15px">
        <label for="password">Password:</label>
        <input type="password" name="pwd" id="password">
    </div>
    <div style="margin-top: 15px">
        <label for="pwd-repeat">Repeat password:</label>
        <input type="password" name="pwd-repeat" id="pwd-repeat">
    </div>
    <div style="margin-top: 15px; margin-left: 50px">
        <button type="submit">Register</button>
    </div>
</form>
</body>
</html>
