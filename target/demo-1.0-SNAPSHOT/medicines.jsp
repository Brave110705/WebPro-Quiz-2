<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Medicine List | Medicine Database</title>
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

    <div class="max-w-4xl mx-auto mt-6 bg-white shadow-md rounded-lg p-6">
        <h2 class="text-lg font-semibold mb-2 text-green-700">Add Category</h2>
        <form action="categories?action=store" method="post" class="flex gap-2">
            <input type="text" name="name" placeholder="Category Name" class="border rounded px-3 py-2 flex-1" required>
            <button type="submit" class="bg-green-600 hover:bg-green-700 text-white px-4 py-2 rounded">
                Add Category
            </button>
        </form>
    </div>

    <div class="max-w-4xl mx-auto mt-10 bg-white shadow-md rounded-lg p-6">
        <h1 class="text-2xl font-bold mb-4 text-green-700">Medicine List</h1>

        <a href="medicines?action=create" class="bg-green-600 hover:bg-green-700 text-white px-4 py-2 rounded mb-4 inline-block">
            Add New Medicine
        </a>

        <form action="medicines" method="get" class="mb-4 flex gap-2">
            <input 
                type="text" 
                name="search" 
                value="${param.search}" 
                placeholder="Search medicine by name..." 
                class="border rounded px-3 py-2 flex-1">
            
            <button type="submit" class="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded">
                Search
            </button>
        </form>

        <table class="min-w-full border-collapse border border-gray-300">
            <thead class="bg-gray-100">
                <tr>
                    <th class="border border-gray-300 px-4 py-2 text-left">ID</th>
                    <th class="border border-gray-300 px-4 py-2 text-left">Name</th>
                    <th class="border border-gray-300 px-4 py-2 text-left">Price</th>
                    <th class="border border-gray-300 px-4 py-2 text-left">Category</th>
                    <th class="border border-gray-300 px-4 py-2 text-left">Stock</th>
                    <th class="border border-gray-300 px-4 py-2 text-left">Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="med" items="${medicines}">
                    <tr class="hover:bg-gray-50">
                        <td class="border border-gray-300 px-4 py-2">${med.id}</td>
                        <td class="border border-gray-300 px-4 py-2">${med.name}</td>
                        <td class="border border-gray-300 px-4 py-2">Rp${med.price}</td>
                        <td class="border border-gray-300 px-4 py-2">${med.categoryName}</td>
                        <td class="border border-gray-300 px-4 py-2">${med.stock}</td>
                    <td class="border border-gray-300 px-4 py-2 text-center">

                        <a href="medicines?action=edit&id=${med.id}" 
                        class="bg-blue-500 hover:bg-blue-600 text-white px-3 py-1 rounded mb-1 inline-block">
                            Edit
                        </a>

                        <a href="medicines?action=delete&id=${med.id}" 
                        onclick="return confirm('Delete this item?')" 
                        class="bg-red-600 hover:bg-red-700 text-white px-3 py-1 rounded inline-block mb-2 block">
                            Delete
                        </a>

                        <div class="flex justify-center space-x-2 mt-2">

                            <form action="medicines" method="post">
                                <input type="hidden" name="action" value="increase" />
                                <input type="hidden" name="id" value="${med.id}" />
                                <button type="submit" class="bg-green-500 hover:bg-green-600 text-white px-3 py-1 rounded">
                                    +
                                </button>
                            </form>

                            <form action="medicines" method="post">
                                <input type="hidden" name="action" value="decrease" />
                                <input type="hidden" name="id" value="${med.id}" />
                                <button type="submit" class="bg-orange-500 hover:bg-yellow-600 text-white px-3 py-1 rounded">
                                    −
                                </button>
                            </form>
                        </div>

                    </td>

                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <a href="index.jsp" class="mt-4 inline-block text-green-700 hover:underline">Back to Home</a>
    </div>

</body>
</html>
