<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản Lý Booking</title>
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
        <h1>Quản Lý Booking</h1>
    </div>

    <div class="button-container">
        <!-- Nút Create Booking -->
        <a href="BookingController?action=create" class="button">Create Booking</a>
        <!-- Nút View Booking -->
        <a href="BookingController?action=view" class="button">View Booking</a>
        <!-- Nút Search Booking -->
        <a href="BookingController?action=search" class="button">Search Booking</a>
        <!-- Nút Delete Booking -->
        <a href="BookingController?action=delete" class="button">Delete Booking</a>
        <!-- Nút Update Booking -->
        <a href="BookingController?action=update" class="button">Update Booking</a>
        <!-- Nút Quay Lại -->
        <a href="home.jsp" class="button" style="background-color: #007BFF;">Quay lại</a>
    </div>

</body>
</html>
