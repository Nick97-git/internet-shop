<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of Orders</title>
</head>
<body>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Details</th>
        <th>Delete</th>
    </tr>

    <c:forEach var="order" items="${orders}">
        <tr>
            <td>
                <c:out value="${order.id}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/orders/details?order_id=${order.id}">
                    Details
                </a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/orders/delete?order_id=${order.id}">
                    Delete
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
