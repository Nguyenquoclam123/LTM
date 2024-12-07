package Model.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.BEAN.Booking;

public class BookingDAO {

    public boolean checkExist(long id) {
        for (Booking booking : getAllBookings()) {
            if (booking.getId() == id)
                return true;
        }
        return false;
    }

    public boolean createNewBooking(Booking booking) {
        if (checkExist(booking.getId())) {
            return false;
        }
        String query = "INSERT INTO booking (id, user_id, table_id, date, status_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dulieu", "root", "");
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setLong(1, booking.getId());
            pstmt.setLong(2, booking.getUser_id());
            pstmt.setLong(3, booking.getTable_id());
            // Chuyển đổi java.util.Date thành java.sql.Date
            pstmt.setDate(4, new java.sql.Date(booking.getDate().getTime())); 
            pstmt.setLong(5, booking.getStatus_id());

            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return false;
    }

    public List<Booking> getAllBookings() {
        List<Booking> list = new ArrayList<>();
        String query = "SELECT * FROM booking";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dulieu", "root", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Booking booking = new Booking();
                booking.setId(rs.getLong("id"));
                booking.setUser_id(rs.getLong("user_id"));
                booking.setTable_id(rs.getLong("table_id"));
                // Lấy ngày dưới dạng java.sql.Date
                booking.setDate(rs.getDate("date")); 
                booking.setStatus_id(rs.getInt("status_id"));
                list.add(booking);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return list;
    }

    public Booking getBookingById(long id) {
        Booking booking = null;
        String query = "SELECT * FROM booking WHERE id = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dulieu", "root", "");
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                booking = new Booking();
                booking.setId(rs.getLong("id"));
                booking.setUser_id(rs.getLong("user_id"));
                booking.setTable_id(rs.getLong("table_id"));
                // Lấy ngày dưới dạng java.sql.Date
                booking.setDate(rs.getDate("date"));
                booking.setStatus_id(rs.getInt("status_id"));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return booking;
    }

    public boolean deleteBooking(long id) {
        String query = "DELETE FROM booking WHERE id = ?";
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

    public boolean updateBooking(Booking booking) {
        String query = "UPDATE booking SET user_id = ?, table_id = ?, date = ?, status_id = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dulieu", "root", "");
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setLong(1, booking.getUser_id());
            pstmt.setLong(2, booking.getTable_id());
            // Chuyển đổi java.util.Date thành java.sql.Date
            pstmt.setDate(3, new java.sql.Date(booking.getDate().getTime())); 
            pstmt.setLong(4, booking.getStatus_id());
            pstmt.setLong(5, booking.getId());

            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return false;
    }
}
