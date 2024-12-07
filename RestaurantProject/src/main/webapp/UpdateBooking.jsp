<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.BEAN.Booking"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Booking</title>
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
        .form-container label, .form-container input {
            display: block;
            margin-bottom: 10px;
        }
        .form-container input {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .form-container button {
            margin-top: 10px;
            padding: 10px;
            background-color: #007BFF;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .form-container button:hover {
            background-color: #0056b3;
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid #ccc;
        }
        th, td {
            padding: 10px;
            text-align: center;
        }
    </style>
</head>
<body>
    <h1>Update Booking</h1>
    <div class="form-container">
        <form action="BookingController?action=update" method="post">
            <label for="id">Booking ID:</label>
            <input type="text" id="id" name="id" readonly>

            <label for="user_id">User ID:</label>
            <input type="text" id="user_id" name="user_id">

            <label for="table_id">Table ID:</label>
            <input type="text" id="table_id" name="table_id">

            <label for="date">Date:</label>
            <input type="text" id="date" name="date">

            <label for="status_id">Status ID:</label>
            <input type="text" id="status_id" name="status_id">

            <button type="submit">Update</button>
        </form>
    </div>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>User ID</th>
                <th>Table ID</th>
                <th>Date</th>
                <th>Status ID</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <%
                ArrayList<Booking> bookings = (ArrayList<Booking>) request.getAttribute("listBookings");
                if (bookings != null) {
                    for (Booking booking : bookings) {
            %>
            <tr>
                <td><%= booking.getId() %></td>
                <td><%= booking.getUser_id() %></td>
                <td><%= booking.getTable_id() %></td>
                <td><%= booking.getDate() %></td>
                <td><%= booking.getStatus_id() %></td>
                <td>
                    <button onclick="fillForm('<%= booking.getId() %>', '<%= booking.getUser_id() %>', '<%= booking.getTable_id() %>', '<%= booking.getDate() %>', '<%= booking.getStatus_id() %>')">Edit</button>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="6">No bookings found</td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>

    <script>
        function fillForm(id, userId, tableId, date, statusId) {
            document.getElementById("id").value = id;
            document.getElementById("user_id").value = userId;
            document.getElementById("table_id").value = tableId;
            document.getElementById("date").value = date;
            document.getElementById("status_id").value = statusId;
        }
    </script>
</body>
</html>
