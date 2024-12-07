<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.BEAN.Booking" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh Sách Booking</title>
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
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            background-color: #fff;
        }
        th, td {
            padding: 12px 15px;
            text-align: left;
        }
        th {
            background-color: #007BFF;
            color: white;
            text-transform: uppercase;
            font-size: 14px;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #d1e7fd;
        }
        td {
            font-size: 14px;
            color: #333;
        }
        a {
            display: block;
            text-align: center;
            margin: 20px auto;
            padding: 10px 20px;
            background-color: #007BFF;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            width: 150px;
        }
        a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h1>Danh Sách Booking</h1>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>User ID</th>
                <th>Table ID</th>
                <th>Ngày Đặt</th>
                <th>Trạng Thái</th>
            </tr>
        </thead>
        <tbody>
        <%
            ArrayList<Booking> bookings = (ArrayList<Booking>) request.getAttribute("BookingList");
            if (bookings != null && !bookings.isEmpty()) {
                for (Booking booking : bookings) {
        %>
            <tr>
                <td><%= booking.getId() %></td>
                <td><%= booking.getUser_id() %></td>
                <td><%= booking.getTable_id() %></td>
                <td><%= booking.getDate() %></td>
                <td><%= booking.getStatus_id() %></td>
            </tr>
        <%
                }
            } else {
        %>
            <tr>
                <td colspan="5" style="text-align: center;">Không có dữ liệu</td>
            </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <a href="Booking/Index.jsp">Quay lại trang chủ</a>
</body>
</html>
