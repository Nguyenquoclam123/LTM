<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản Lý Bàn</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 20px;
        }
        .header {
            text-align: center;
            margin-bottom: 30px;
        }
        .header h1 {
            font-size: 28px;
            color: #333;
        }
        .button-container {
            text-align: center;
            margin-bottom: 20px;
        }
        .button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            margin: 10px;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
        }
        .button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

    <div class="header">
        <h1>Quản Lý Bàn</h1>
    </div>

    <div class="button-container">
        <!-- Nút Create Table -->
        <a href="TablesController?action=create" class="button">Create Table</a>
        <!-- Nút View Tables -->
        <a href="TablesController?action=view" class="button">View Tables</a>
        <!-- Nút Search Table -->
        <a href="TablesController?action=search" class="button">Search Table</a>
        <!-- Nút Delete Table -->
        <a href="TablesController?action=delete" class="button">Delete Table</a>
        <!-- Nút Update Table -->
        <a href="TablesController?action=update" class="button">Update Table</a>
        <!-- Nút Quay lại -->
        <a href="home.jsp" class="button" style="background-color: #007BFF;">Quay lại</a>
    </div>
</body>
</html>
