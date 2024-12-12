package Model.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.BEAN.Booking;

public class BookingDAO {

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

    // Kiểm tra sự tồn tại của booking trong hệ thống
    public boolean checkExist(long id) {
        for (Booking booking : getAllBookings()) {
            if (booking.getId() == id)
                return true;
        }
        return false;
    }

    // Lấy tất cả các booking
    public List<Booking> getAllBookings() {
        List<Booking> list = new ArrayList<>();
        String query = "SELECT * FROM booking";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setId(rs.getLong("id"));
                booking.setUser_id(rs.getLong("user_id"));
                booking.setTable_id(rs.getLong("table_id"));
                booking.setDate(rs.getDate("date"));
                booking.setStatus_id(rs.getInt("status_id"));
                list.add(booking);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi lấy tất cả booking: " + e.getMessage());
        }
        return list;
    }
    
 

    // Thêm booking mới
    public boolean addBooking(Booking booking) {
        if (checkExist(booking.getId())) {
            return false;
        }
        String query = "INSERT INTO booking (id, user_id, table_id, date, status_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setLong(1, booking.getId());
            pstmt.setLong(2, booking.getUser_id());
            pstmt.setLong(3, booking.getTable_id());
            pstmt.setDate(4, new java.sql.Date(booking.getDate().getTime()));
            pstmt.setLong(5, booking.getStatus_id());

            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi thêm booking: " + e.getMessage());
        }
        return false;
    }

    // Cập nhật thông tin booking
    public boolean updateBooking(Booking booking) {
        String query = "UPDATE booking SET user_id = ?, table_id = ?, date = ?, status_id = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setLong(1, booking.getUser_id());
            pstmt.setLong(2, booking.getTable_id());
            pstmt.setDate(3, new java.sql.Date(booking.getDate().getTime()));
            pstmt.setLong(4, booking.getStatus_id());
            pstmt.setLong(5, booking.getId());

            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi cập nhật booking: " + e.getMessage());
        }
        return false;
    }

    // Xóa booking dựa trên ID
    public boolean deleteBooking(long id) {
        String query = "DELETE FROM booking WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setLong(1, id);

            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi xóa booking: " + e.getMessage());
        }
        return false;
    }

    // Tìm kiếm booking theo cột và chuỗi tìm kiếm
    public List<Booking> searchByCol(String searchBy, String searchString) {
        List<Booking> list = new ArrayList<>();
        String query = "SELECT * FROM booking WHERE " + searchBy + " LIKE ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, "%" + searchString + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Booking booking = new Booking();
                    booking.setId(rs.getLong("id"));
                    booking.setUser_id(rs.getLong("user_id"));
                    booking.setTable_id(rs.getLong("table_id"));
                    booking.setDate(rs.getDate("date"));
                    booking.setStatus_id(rs.getInt("status_id"));
                    list.add(booking);
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi tìm kiếm booking: " + e.getMessage());
        }
        return list;
    }

    // Lấy thông tin một booking dựa trên ID
    public Booking getBookingById(long id) {
        Booking booking = null;
        String query = "SELECT * FROM booking WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    booking = new Booking();
                    booking.setId(rs.getLong("id"));
                    booking.setUser_id(rs.getLong("user_id"));
                    booking.setTable_id(rs.getLong("table_id"));
                    booking.setDate(rs.getDate("date"));
                    booking.setStatus_id(rs.getInt("status_id"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi lấy thông tin booking theo ID: " + e.getMessage());
        }
        return booking;
    }
}
