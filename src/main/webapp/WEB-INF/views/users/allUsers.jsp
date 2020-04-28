<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of Users</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/registration">
    <br/>
    <button type="submit">Create new User</button>
</form>
<h1>All users: </h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Login</th>
        <th>Name</th>
        <th>Delete</th>
    </tr>

    <c:forEach var="user" items="${users}">
        <tr>
            <td>
                <c:out value="${user.id}"/>
            </td>
            <td>
                <c:out value="${user.login}"/>
            </td>
            <td>
                <c:out value="${user.name}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/users/delete?user_id=${user.id}">
                    Delete
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
