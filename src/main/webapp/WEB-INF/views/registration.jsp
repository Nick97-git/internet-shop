<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
</head>
<body>
<div style="width: 100%; height: 100%; display: flex; align-items: center; justify-content: center">
    <form method="post" action="${pageContext.request.contextPath}/registration">
        <div class="form-group" style="width: 500px">
            <label for="name" style="font-family: Garamond,serif; font-size: larger">Name</label>
            <input type="text" name="name" class="form-control" id="name">
        </div>
        <div class="form-group">
            <label for="login" style="font-family: Garamond,serif; font-size: larger">Login</label>
            <input type="text" name="login" class="form-control" id="login">
        </div>
        <div class="form-group">
            <label for="password" style="font-family: Garamond,serif; font-size: larger">Password</label>
            <input type="password" name="pwd" class="form-control" id="password">
            <small id="pwdHelp" style="color: red; font-family: Garamond,serif; font-size: larger">${message}</small>
        </div>
        <div class="form-group">
            <label for="pwd-repeat" style="font-family: Garamond,serif; font-size: larger">Repeat password</label>
            <input type="password" name="pwd-repeat" class="form-control" id="pwd-repeat">
        </div>
        <button type="submit" class="btn btn-info" style="width: 100%">Register</button>
    </form>
</div>
</body>
</html>
