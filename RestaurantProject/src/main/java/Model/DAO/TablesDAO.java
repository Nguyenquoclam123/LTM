package Model.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.BEAN.Tables;

public class TablesDAO {

    public boolean checkExist(long id) {
        for (Tables table : getAllTables()) {
            if (table.getId() == id)
                return true;
        }
        return false;
    }

    public boolean createNewTable(Tables table) {
        if (checkExist(table.getId())) {
            return false;
        }
        String query = "INSERT INTO tables (id, number, status_id) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dulieu", "root", "");
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setLong(1, table.getId());
            pstmt.setString(2, table.getNumber());
            pstmt.setInt(3, table.getStatus_id());

            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return false;
    }

    public List<Tables> getAllTables() {
        List<Tables> list = new ArrayList<>();
        String query = "SELECT * FROM tables";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dulieu", "root", "");
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
            System.out.println("Lỗi: " + e.getMessage());
        }
        return list;
    }

    public Tables getTableById(long id) {
        Tables table = null;
        String query = "SELECT * FROM tables WHERE id = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dulieu", "root", "");
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                table = new Tables();
                table.setId(rs.getLong("id"));
                table.setNumber(rs.getString("number"));
                table.setStatus_id(rs.getInt("status_id"));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return table;
    }

    public boolean deleteTable(long id) {
        String query = "DELETE FROM tables WHERE id = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dulieu", "root", "");
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setLong(1, id);

            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return false;
    }

    public boolean updateTable(Tables table) {
        String query = "UPDATE tables SET number = ?, status_id = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dulieu", "root", "");
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, table.getNumber());
            pstmt.setInt(2, table.getStatus_id());
            pstmt.setLong(3, table.getId());

            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return false;
    }
}
