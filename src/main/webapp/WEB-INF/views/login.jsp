<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h4 style="color: red">${errorMsg}</h4>
<form method="post" action="${pageContext.request.contextPath}/login">
    <div>
        <label for="login">Login:</label>
        <input type="text" name="login" id="login">
    </div>
    <div style="margin-top: 15px">
        <label for="password">Password:</label>
        <input type="password" name="pwd" id="password">
    </div>
    <div style="margin-top: 15px; margin-left: 50px">
        <button type="submit">Login</button>
    </div>
</form>
</body>
</html>
