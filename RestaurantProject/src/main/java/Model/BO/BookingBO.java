package Model.BO;

import java.util.List;

import Model.BEAN.Booking;
import Model.DAO.BookingDAO;

public class BookingBO {
    private BookingDAO dao = new BookingDAO();

    public boolean create(Booking booking) {
        return this.dao.createNewBooking(booking);
    }

    public boolean delete(long id) {
        if (!this.dao.checkExist(id)) {
            return false;
        }
        return this.dao.deleteBooking(id);
    }

    public boolean update(Booking booking) {
        if (!this.dao.checkExist(booking.getId())) {
            return false;
        }
        return this.dao.updateBooking(booking);
    }

    public List<Booking> getAllBookings() {
        return this.dao.getAllBookings();
    }

    public Booking getBookingById(long id) {
        if (!this.dao.checkExist(id)) {
            return null;
        }
        return this.dao.getBookingById(id);
    }
}
