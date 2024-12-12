<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="Model.BEAN.Booking"%>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <title>Tìm Kiếm Thông Tin Đặt Bàn</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 20px;
        }

        .container {
            max-width: 800px;
            margin: auto;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        h3 {
            text-align: center;
            color: #333;
        }

        .search-form {
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: inline-block;
            margin-right: 10px;
        }

        button {
            padding: 10px 20px;
            background-color: #007BFF;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }

        input[type="text"], select {
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            width: 100%;
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
            <h3>Tìm Kiếm Thông Tin Đặt Bàn</h3>

            <!-- Form tìm kiếm -->
            <form action="BookingController?action=search" method="post">
                <div class="form-group">
                    <label for="searchBy">Tìm kiếm theo:</label>
                    <select id="searchBy" name="searchBy">
                        <option value="id">Mã Đặt Bàn</option>
                        <option value="user_id">ID Người Dùng</option>
                        <option value="table_id">ID Bàn</option>
                        <option value="status_id">Trạng Thái</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="searchString">Nhập thông tin tìm kiếm:</label>
                    <input type="text" id="searchString" name="searchString" placeholder="Nhập từ khóa tìm kiếm">
                </div>

                <div class="form-group">
                    <button type="submit">Tìm Kiếm</button>
                    <button type="reset">Reset</button>
                </div>
            </form>
        </div>

        <!-- Hiển thị kết quả tìm kiếm -->
        <%
            List<Booking> bookingList = (List<Booking>) request.getAttribute("bookingList");

            if (bookingList != null && !bookingList.isEmpty()) {
        %>
            <table>
                <thead>
                    <tr>
                        <th>Mã Đặt Bàn</th>
                        <th>ID Người Dùng</th>
                        <th>ID Bàn</th>
                        <th>Ngày Đặt</th>
                        <th>Trạng Thái</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Booking booking : bookingList) { %>
                        <tr>
                            <td><%= booking.getId() %></td>
                            <td><%= booking.getUser_id() %></td>
                            <td><%= booking.getTable_id() %></td>
                            <td><%= booking.getDate() %></td>
                            <td>
						    <% 
						        long statusId = booking.getStatus_id();
						        String status = "";
						        if (statusId == 5) {
						            status = "Waiting";
						        } else if (statusId == 6) {
						            status = "Confirm";
						        } else if (statusId == 7) {
						            status = "Canceled";
						        }
						    %>
						    <%= status %>
						</td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        <%
            } else {
        %>
            <p>Không tìm thấy thông tin đặt bàn.</p>
        <%
            }
        %>
    </div>

</body>

</html>
