<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of Products</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/products/add">
    <br/>
    <button type="submit">Create new Product</button>
</form>
<h1>All products: </h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Add</th>
    </tr>

    <c:forEach var="product" items="${products}">
        <tr>
            <td>
                <c:out value="${product.id}"/>
            </td>
            <td>
                <c:out value="${product.name}"/>
            </td>
            <td>
                <c:out value="${product.price}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/shopping-cart/add?cart_id=1&product_id=${product.id}">
                    Add
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
