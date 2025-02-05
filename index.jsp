<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Calculator</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="calculator-container">
        <h2>Calculator</h2>
        <form action="CalculatorServlet" method="post">
            <input type="number" name="num1" placeholder="Enter First Number" required>
            <select name="operation">
                <option value="add">+</option>
                <option value="subtract">-</option>
                <option value="multiply">×</option>
                <option value="divide">÷</option>
            </select>
            <input type="number" name="num2" placeholder="Enter Second Number" required>
            <button type="submit">Calculate</button>
        </form>

        <%-- Error Message Display --%>
        <% String error = request.getParameter("error"); %>
        <% if (error != null) { %>
            <div class="error-message"><%= error %></div>
        <% } %>

        <%-- Result Display --%>
        <% String result = request.getParameter("result"); %>
        <% if (result != null) { %>
            <div class="result-display"><strong>Last Result:</strong> <%= result %></div>
        <% } %>
    </div>
</body>
</html>
