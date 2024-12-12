<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.BEAN.Tables" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Xóa Bàn</title>
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
        input, button {
            cursor: pointer;
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
    <h1>Danh Sách Bàn</h1>
    <table border="1">
        <thead>
            <tr>
                <th>ID Bàn</th>
                <th>Số Bàn</th>
                <th>ID Trạng Thái</th>
                <th>Hành Động</th>
            </tr>
        </thead>
        <tbody>
        <% 
            ArrayList<Tables> danhSachBan = (ArrayList<Tables>) request.getAttribute("tablesList");
            if (danhSachBan == null || danhSachBan.isEmpty()) { %>
                <tr>
                    <td colspan="4" style="text-align: center;">Danh sách bàn hiện tại trống</td>
                </tr>
        <%  } else { 
                for (Tables table : danhSachBan) { %>
                    <tr>
                        <td><%= table.getId() %></td>
                        <td><%= table.getNumber() %></td>
                        <td><%= table.getStatus_id()== 1 ? "Còn Trống" : "Đã hết" %></td>
                        <td>
                            <form action="TablesController?action=delete" method="post" style="display: inline;">
                                <!-- Gửi ID bàn qua request -->
                                <input type="hidden" name="id" value="<%= table.getId() %>">
                                <button type="submit" name="action" value="Delete">Xóa</button>
                            </form>
                        </td>
                    </tr>
        <%      } 
            } %>
        </tbody>
    </table>
    <a href="Index_Tables.jsp">Quay về Trang Chủ</a>
</body>
</html>
