<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Add Product</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
</head>
<body>
<div style="width: 100%; height: 100%; display: flex; align-items: center; justify-content: center">
    <form method="post" action="${pageContext.request.contextPath}/products/add">
        <div class="form-group" style="width: 500px">
            <label for="name" style="font-family: Garamond,serif; font-size: larger">Name</label>
            <input type="text" name="name" class="form-control" id="name">
        </div>
        <div class="form-group">
            <label for="price" style="font-family: Garamond,serif; font-size: larger">Price</label>
            <input type="text" name="price" class="form-control" id="price">
        </div>
        <button type="submit" class="btn btn-info" style="width: 100%">Add Product</button>
    </form>
</div>
</body>
</html>
