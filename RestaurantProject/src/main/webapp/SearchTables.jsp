<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="Model.BEAN.Tables"%>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <title>Tìm Kiếm Thông Tin Bàn</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        .container {
            width: 60%;
            margin: auto;
            text-align: center;
        }

        .search-form {
            margin-bottom: 20px;
            border: 1px solid #ccc;
            padding: 10px;
        }

        .form-group {
            display: flex;
            align-items: center;
            justify-content: center;
            margin-bottom: 10px;
        }

        label {
            display: inline-block;
            margin-right: 5px;
            vertical-align: middle;
        }

        button {
            margin: 0px 4px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table, th, td {
            border: 1px solid #ccc;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }
    </style>
</head>

<body>

    <div class="container">
        <div class="search-form">
            <h3>Tìm Kiếm Thông Tin Bàn</h3>

            <!-- Form tìm kiếm -->
            <form action="TablesController?action=search" method="post">
                <div class="form-group">
                    <input type="radio" name="searchBy" value="ID" id="ID" checked>
                    <label for="ID">ID Bàn</label>
                    <input type="radio" name="searchBy" value="Number" id="Number">
                    <label for="Number">Số Bàn</label>
                    <input type="radio" name="searchBy" value="status_id" id="status_id">
                    <label for="status_id">Trạng Thái</label>
                </div>

                <!-- Nhập từ khóa tìm kiếm -->
                <div class="form-group">
                    <label for="input">Nhập thông tin tìm kiếm: </label>
                    <input type="text" id="input" name="searchString">
                </div>

                <!-- Chọn trạng thái -->
                <div class="form-group">
                    <label for="statusSelect">Chọn trạng thái: </label>
                    <select id="statusSelect" name="status_id">
                        <option value="">-- Chọn trạng thái --</option>
                        <option value="1">Còn Trống</option>
                        <option value="2">Đã Được Đặt</option>
                    </select>
                </div>

                <!-- Submit và Reset -->
                <div class="form-group">
                    <button type="submit">Tìm Kiếm</button>
                    <button type="reset">Reset</button>
                </div>
            </form>
        </div>

        <!-- Hiển thị kết quả tìm kiếm -->
        <%
            // Lấy danh sách kết quả tìm kiếm từ request attribute
            List<Tables> tablesList = (List<Tables>) request.getAttribute("tablesList");

            if (tablesList != null && !tablesList.isEmpty()) {
        %>
            <table>
                <thead>
                    <tr>
                        <th>ID Bàn</th>
                        <th>Số Bàn</th>
                        <th>Trạng Thái</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Tables table : tablesList) { %>
                        <tr>
                            <td><%= table.getId() %></td>
                            <td><%= table.getNumber() %></td>
                            <td><%= table.getStatus_id() == 1 ? "Còn Trống" : "Đã Được Đặt" %></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        <%
            } else {
        %>
            <p>Không tìm thấy bàn nào.</p>
        <%
            }
        %>
    </div>

</body>

</html>
