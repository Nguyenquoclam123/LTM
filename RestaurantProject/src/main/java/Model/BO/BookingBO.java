package Model.BO;

import java.util.List;

import Model.BEAN.Booking;
import Model.DAO.BookingDAO;

public class BookingBO {
    private static BookingDAO dao = new BookingDAO(); // Sử dụng biến static cho DAO

    // Tạo mới một booking
    public static boolean create(Booking booking) {
        return dao.createNewBooking(booking);
    }

    // Xóa một booking dựa trên ID
    public static boolean delete(long id) {
        if (!dao.checkExist(id)) {
            return false;
        }
        return dao.deleteBooking(id);
    }

    // Cập nhật thông tin một booking
    public static boolean update(Booking booking) {
        if (!dao.checkExist(booking.getId())) {
            return false;
        }
        return dao.updateBooking(booking);
    }

    // Lấy danh sách tất cả booking
    public static List<Booking> getAllBookings() {
        return dao.getAllBookings();
    }

    // Lấy thông tin một booking dựa trên ID
    public static Booking getBookingById(long id) {
        if (!dao.checkExist(id)) {
            return null;
        }
        return dao.getBookingById(id);
    }
}
