<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${medicine == null ? "Add" : "Edit"} Medicine | Medicine Database</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-green-50 text-gray-800">

    <nav class="bg-green-600 text-white px-6 py-4 shadow-md">
        <div class="flex justify-between items-center">
            <h1 class="text-xl font-bold">💊 Medicine Database</h1>
            <div class="space-x-4">
                <a href="index.jsp" class="hover:underline">Home</a>
                <a href="medicines" class="hover:underline">Database</a>
            </div>
        </div>
    </nav>

    <div class="max-w-3xl mx-auto mt-10 bg-white shadow-md rounded-lg p-6">
        <h1 class="text-2xl font-bold mb-4 text-green-700">${medicine == null ? "Add" : "Edit"} Medicine</h1>

        <form action="medicines" method="post" class="space-y-4">
            <input type="hidden" name="id" value="${medicine.id}"/>

            <div>
                <label class="block font-semibold mb-1">Medicine Name</label>
                <input type="text" name="name" value="${medicine.name}" class="border rounded px-3 py-2 w-full" required>
            </div>

            <div>
                <label class="block font-semibold mb-1">Price</label>
                <input type="number" step="0.01" name="price" value="${medicine.price}" class="border rounded px-3 py-2 w-full" required>
            </div>

            <div>
                <label class="block font-semibold mb-1">Stock</label>
                <input type="number" name="stock" value="${medicine.stock}" class="border rounded px-3 py-2 w-full" required>
            </div>

            <div>
                <label class="block font-semibold mb-1">Category</label>
                <select name="categoryId" class="border rounded px-3 py-2 w-full">
                    <option value="">Select Category</option>
                    <c:forEach var="cat" items="${categories}">
                        <option value="${cat.id}" ${medicine != null && medicine.categoryId == cat.id ? "selected" : ""}>
                            ${cat.name}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="flex justify-between mt-6">
                <a href="medicines" class="text-gray-600 hover:underline">Back</a>
                <button type="submit" class="bg-green-600 hover:bg-green-700 text-white px-4 py-2 rounded">
                    Save Changes
                </button>
            </div>
        </form>
    </div>

</body>
</html>
