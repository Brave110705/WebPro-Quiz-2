<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${category == null ? "Add" : "Edit"} Category</title>
</head>
<body>
<h2>${category == null ? "Add" : "Edit"} Category</h2>

<form action="categories" method="post">
    <input type="hidden" name="id" value="${category.id}"/>

    <label>Name:</label><br/>
    <input type="text" name="name" value="${category.name}" required/><br/><br/>

    <button type="submit">Save</button>
</form>

<a href="categories">Back to Category List</a>
</body>
</html>
