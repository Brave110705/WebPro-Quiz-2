<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Categories</title>
</head>
<body>
<h2>Category List</h2>

<a href="categories?action=create">Add New Category</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="cat" items="${categories}">
        <tr>
            <td>${cat.id}</td>
            <td>${cat.name}</td>
            <td>
                <a href="categories?action=edit&id=${cat.id}">Edit</a>
                <a href="categories?action=delete&id=${cat.id}" onclick="return confirm('Delete this item?')">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="index.jsp">Back to Home</a>
</body>
</html>
