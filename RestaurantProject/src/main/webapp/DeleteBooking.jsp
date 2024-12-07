<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.BEAN.Booking" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Xóa Booking</title>
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
        button {
            cursor: pointer;
            background-color: #ff4d4d;
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 5px;
        }
        button:hover {
            background-color: #e60000;
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
                <th>Mã Booking</th>
                <th>User ID</th>
                <th>Table ID</th>
                <th>Ngày</th>
                <th>Status ID</th>
                <th>Hành Động</th>
            </tr>
        </thead>
        <tbody>
        <% 
            ArrayList<Booking> danhSachBooking = (ArrayList<Booking>) request.getAttribute("listBooking");
            if (danhSachBooking == null || danhSachBooking.isEmpty()) { %>
                <tr>
                    <td colspan="6" style="text-align: center;">Danh sách booking hiện tại trống</td>
                </tr>
        <%  } else { 
                for (Booking booking : danhSachBooking) { %>
                    <tr>
                        <td><%= booking.getId() %></td>
                        <td><%= booking.getUser_id() %></td>
                        <td><%= booking.getTable_id() %></td>
                        <td><%= booking.getDate() %></td>
                        <td><%= booking.getStatus_id() %></td>
                        <td>
                            <form action="BookingController?action=delete" method="post" style="display: inline;">
                                <input type="hidden" name="id" value="<%= booking.getId() %>">
                                <button type="submit">Xóa</button>
                            </form>
                        </td>
                    </tr>
        <%      } 
            } %>
        </tbody>
    </table>
    <a href="Booking/Index.jsp">Quay về Trang Chủ</a>
</body>
</html>
