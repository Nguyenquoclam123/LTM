<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="Model.BEAN.Booking"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm Đặt Bàn</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 20px;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        .form-container {
            width: 80%;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        .form-container label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            color: #333;
        }
        .form-container input,
        .form-container select {
            width: calc(100% - 20px);
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
        }
        .form-container input[type="submit"],
        .form-container input[type="reset"] {
            background-color: #007BFF;
            color: white;
            border: none;
            padding: 10px 15px;
            margin-right: 10px;
            border-radius: 4px;
            cursor: pointer;
        }
        .form-container input[type="submit"]:hover,
        .form-container input[type="reset"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h1>Thêm Đặt Bàn</h1>
    <div class="form-container">
        <form action="BookingController?action=create" method="post" onsubmit="return validateForm()">
            <label for="UserID">ID Người Dùng:</label>
            <input type="text" id="UserID" name="UserID" required placeholder="Nhập ID người dùng">

            <label for="TableID">ID Bàn:</label>
            <input type="text" id="TableID" name="TableID" required placeholder="Nhập ID bàn">

            <label for="Date">Ngày Đặt:</label>
            <input type="date" id="Date" name="Date" required>

            <label for="StatusID">Trạng Thái:</label>
            <input type="text" id="StatusID" name="StatusID" required placeholder="Nhập trạng thái">

            <input type="submit" value="Thêm">
            <input type="reset" value="Reset">
        </form>
    </div>

    <script>
        // Kiểm tra dữ liệu nhập vào
        function validateForm() {
            const userID = document.getElementById('UserID').value.trim();
            const tableID = document.getElementById('TableID').value.trim();
            const date = document.getElementById('Date').value.trim();
            const statusID = document.getElementById('StatusID').value.trim();

            if (!userID || !tableID || !date || !statusID) {
                alert("Vui lòng điền đầy đủ thông tin!");
                return false;
            }

            // Thêm kiểm tra nếu cần
            return true;
        }
    </script>
</body>
</html>
