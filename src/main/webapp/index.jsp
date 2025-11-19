<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Medicine Database</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-green-50 text-gray-800">

    <nav class="bg-green-600 text-white px-6 py-4 shadow-md">
        <div class="flex justify-between items-center">
            <h1 class="text-xl font-bold">💊 Medicine Database</h1>
            <div class="space-x-4">
                <a href="${pageContext.request.contextPath}/" class="hover:underline">Home</a>
                <a href="${pageContext.request.contextPath}/medicines" class="hover:underline">Database</a>

                <!-- <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <span class="font-semibold">${sessionScope.user.name}</span>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/login.jsp" class="hover:underline">Login</a>
                    </c:otherwise>
                </c:choose> -->

            </div>
        </div>
    </nav>

    <main class="max-w-3xl mx-auto mt-10 text-center">
        <h2 class="text-3xl font-bold mb-4">Welcome to the Medicine Database</h2>
        <p class="text-lg text-gray-700 mb-6">
                    Welcome to a Medicine Database Website!
                    This website is used for a Web Programming Assignment
        </p>

        <!-- <c:choose>
            <c:when test="${not empty sessionScope.user}">
                <p class="text-lg text-gray-700 mb-6">
                    ✅ You are logged in as <strong>${sessionScope.user.name}</strong>!
                    You can now modify the database.
                </p>
            </c:when>
            <c:otherwise>
                <p class="text-lg text-red-600 mb-6 font-semibold">
                    ⚠️ You are not logged in! You won’t be able to modify the database.
                </p>
            </c:otherwise>
        </c:choose> -->

        <!-- <p class="text-lg text-gray-600 mb-6">
            masi gtw mw ditaruh ap
        </p> -->

        <a href="${pageContext.request.contextPath}/medicines"
           class="bg-green-600 text-white px-5 py-3 rounded-lg shadow hover:bg-green-700 transition">
            Go to Database
        </a>
    </main>

    <!-- <c:if test="${not empty sessionScope.user}">
        <form method="POST" action="${pageContext.request.contextPath}/logout" class="fixed bottom-1 right-0">
            <button type="submit"
                class="text-red-600 hover:text-red-800 font-semibold underline transition">
                Log out
            </button>
        </form>
    </c:if> -->

</body>
</html>
