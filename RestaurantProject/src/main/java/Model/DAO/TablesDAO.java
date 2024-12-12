package Model.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.BEAN.Tables;

public class TablesDAO {

    // Kết nối cơ sở dữ liệu
    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/restaurant"; 
        String user = "root";  
        String password = ""; 
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Lỗi kết nối: " + e.getMessage());
            throw new SQLException(e);
        }
    }

    // Kiểm tra sự tồn tại của bàn trong hệ thống
    public boolean checkExist(long id) {
        for (Tables table : getAllTables()) {
            if (table.getId() == id)
                return true;
        }
        return false;
    }

    // Lấy tất cả các bàn
    public List<Tables> getAllTables() {
        List<Tables> list = new ArrayList<>();
        String query = "SELECT * FROM tables";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Tables table = new Tables();
                table.setId(rs.getLong("id"));
                table.setNumber(rs.getString("number"));
                table.setStatus_id(rs.getInt("status_id"));
                list.add(table);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi lấy tất cả bàn: " + e.getMessage());
        }
        return list;
    }

    // Thêm bàn mới
    public boolean addTable(Tables table) {
        if (checkExist(table.getId())) {
            return false;
        }
        String query = "INSERT INTO tables (id, number, status_id) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setLong(1, table.getId());
            pstmt.setString(2, table.getNumber());
            pstmt.setInt(3, table.getStatus_id());

            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi thêm bàn: " + e.getMessage());
        }
        return false;
    }

    // Cập nhật thông tin bàn
    public boolean updateTable(Tables table) {
        String query = "UPDATE tables SET number = ?, status_id = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, table.getNumber());
            pstmt.setInt(2, table.getStatus_id());
            pstmt.setLong(3, table.getId());

            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi cập nhật bàn: " + e.getMessage());
        }
        return false;
    }

    // Xóa bàn dựa trên ID
    public boolean deleteTable(long id) {
        String query = "DELETE FROM tables WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setLong(1, id);

            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi xóa bàn: " + e.getMessage());
        }
        return false;
    }

    // Tìm kiếm bàn theo cột và chuỗi tìm kiếm
    public List<Tables> searchByCol(String searchBy, String searchString) {
        List<Tables> list = new ArrayList<>();
        String query = "SELECT * FROM tables WHERE " + searchBy + " LIKE ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, "%" + searchString + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Tables table = new Tables();
                    table.setId(rs.getLong("id"));
                    table.setNumber(rs.getString("number"));
                    table.setStatus_id(rs.getInt("status_id"));
                    list.add(table);
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi tìm kiếm bàn: " + e.getMessage());
        }
        return list;
    }

    // Lấy thông tin một bàn dựa trên ID
    public Tables getTableById(long id) {
        Tables table = null;
        String query = "SELECT * FROM tables WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    table = new Tables();
                    table.setId(rs.getLong("id"));
                    table.setNumber(rs.getString("number"));
                    table.setStatus_id(rs.getInt("status_id"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi lấy thông tin bàn theo ID: " + e.getMessage());
        }
        return table;
    }
    
 // Lấy tất cả các id bàn
    public List<Long> getAllTablesId() {
        List<Long> ids = new ArrayList<>(); // Danh sách để lưu ID
        String query = "SELECT id FROM tables"; // Chỉ lấy cột id
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                ids.add(rs.getLong("id")); // Thêm id vào danh sách
            }
        } catch (SQLException e) {
            System.out.println("Lỗi lấy tất cả ID bàn: " + e.getMessage());
        }
        return ids; // Trả về danh sách ID
    }

    
}
