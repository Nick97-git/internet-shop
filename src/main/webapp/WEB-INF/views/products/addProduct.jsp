<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Product</title>
</head>
<body>
<h2>Enter information about new product: </h2>
<form method="post" action="${pageContext.request.contextPath}/products/add">
    <div>
        <label for="name">Name:</label>
        <input type="text" name="name" id="name">
    </div>
    <div style="margin-top: 15px">
        <label for="price">Price:</label>
        <input type="text" name="price" id="price">
    </div>
    <div style="margin-top: 15px; margin-left: 50px">
        <button type="submit">Add Product</button>
    </div>
</form>
</body>
</html>
