<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-green-50 text-center pt-12">
    <h1 class="text-3xl font-bold mb-6">Login</h1>
    
    <form action="${pageContext.request.contextPath}/login" method="POST">
        <input type="text" name="username" placeholder="Name"
               class="w-full px-4 py-2 border rounded-lg mb-4" required />
        <button type="submit" class="bg-green-600 text-white px-4 py-2 rounded-lg hover:bg-green-700">
            Login
        </button>
    </form>
</body>
</html>
