<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
</head>
<body>
<div style="width: 100%; height: 100%; display: flex; align-items: center; justify-content: center">
    <form method="post" action="${pageContext.request.contextPath}/login">
        <div class="form-group" style="width: 300px">
            <label for="login" style="font-family: Garamond,serif; font-size: larger">Login</label>
            <input type="text" name="login" class="form-control" id="login">
            <small id="emailHelp" style="color: red; font-family: Garamond,serif; font-size: larger">${requestScope.errorMsg}</small>
        </div>
        <div class="form-group">
            <label for="password" style="font-family: Garamond,serif; font-size: larger">Password</label>
            <input type="password" name="pwd" class="form-control" id="password">
        </div>
        <button type="submit" class="btn btn-success" style="width: 100%">Login</button>
    </form>
</div>
</body>
</html>
