<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="Model.BEAN.Tables"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Table</title>
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
        .form-container input[type="text"],
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
        button{
            cursor:pointer;
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
    <h1>Update Table</h1>
    <div class="form-container">
        <form action="TablesController?action=update" method="post">
            <label for="id">ID:</label>
            <input type="text" id="id" name="id" readonly="readonly">

            <label for="number">Number:</label>
            <input type="text" id="number" name="number" placeholder="Enter table number" required>

            <label for="status_id">Status ID:</label>
            <select id="status_id" name="status_id" required>
			    <option value="" disabled selected>Chọn trạng thái</option>
			    <option value="1">Còn Trống</option>
			    <option value="2">Đã Được Đặt</option>
			</select>

            <input type="submit" name="action" value="Update">
            <input type="reset" value="Reset">
        </form>
    </div>

    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Number</th>
                <th>Status ID</th>
                <th>Update</th>
            </tr>
        </thead>
        <tbody>
        <%
            ArrayList<Tables> list = (ArrayList<Tables>) request.getAttribute("tablesList");
            if (list != null) {
                for (Tables table : list) {
        %>
            <tr>
                <td><%= table.getId() %></td>
                <td><%= table.getNumber() %></td>
                <td><%= table.getStatus_id()== 1 ? "Còn Trống" : "Đã hết" %></td>
                <td>
                    <button onclick="handleUpdate('<%= table.getId() %>', '<%= table.getNumber() %>', '<%= table.getStatus_id() %>')">Update</button>
                </td>
            </tr>
        <%
                }
            } else {
        %>
            <tr>
                <td colspan="4" style="text-align: center;">No data available</td>
            </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <a href="Index_Tables.jsp">Back to Home</a>
    <script type="text/javascript">
        function handleUpdate(id, number, statusID) {
            document.getElementById("id").value = id;
            document.getElementById("number").value = number;
            document.getElementById("status_id").value = statusID;
        }
    </script>
</body>
</html>
