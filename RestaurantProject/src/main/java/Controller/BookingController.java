package Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Model.BEAN.Booking;
import Model.BEAN.Tables;
import Model.BO.BookingBO;
import Model.BO.TablesBO;

@WebServlet("/BookingController")
public class BookingController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BookingController() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "index":
                index(request, response);
                break;
            case "create":
                create(request, response);
                break;
            case "update":
                edit(request, response);
                break;
            case "delete":
                delete(request, response);
                break;
            case "view":
                view(request, response);
                break;
            case "search":
            	search(request, response);
                break;
            default:
                index(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "create":
                create(request, response);
                break;
            case "update":
                edit(request, response);
                break;
            case "delete":
                delete(request, response);
                break;
            case "view":
                view(request, response);
                break;
            case "search":
            	search(request, response);
                break;
            default:
                response.sendRedirect("BookingController?action=index");
                break;
        }
    }

    // Liệt kê danh sách booking
    private void index(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Booking> bookingList = BookingBO.getAllBookings();
        request.setAttribute("bookingList", bookingList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Index_booking.jsp");
        dispatcher.forward(request, response);
    }

    // Tạo mới booking
    private void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getMethod();

        if (method.equalsIgnoreCase("GET")) {
        	// Lấy danh sách tất cả các bàn từ database qua BO
            List<Long> tablesList = TablesBO.getAllTablesId();

            request.setAttribute("tablesList", tablesList);
        	// Lấy danh sách tất cả các booking từ database qua BO
            List<Booking> bookingsList = BookingBO.getAllBookings();
         // Đưa danh sách booking vào request
            request.setAttribute("bookingsList", bookingsList);

            RequestDispatcher dispatcher = request.getRequestDispatcher("CreateBooking.jsp");
            dispatcher.forward(request, response);
        } else if (method.equalsIgnoreCase("POST")) {
            long userId = Long.parseLong(request.getParameter("user_id"));
            long tableId = Long.parseLong(request.getParameter("table_id"));
            long statusId = Long.parseLong(request.getParameter("status_id"));
            String date = request.getParameter("date");

            Booking booking = new Booking();
            booking.setUser_id(userId);
            booking.setTable_id(tableId);
            booking.setStatus_id(statusId);
            booking.setDate(java.sql.Date.valueOf(date));

            BookingBO.create(booking);
            response.sendRedirect("BookingController?action=index");
        }
    }

    // Cập nhật booking
    private void edit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getMethod();

        if (method.equalsIgnoreCase("GET")) {
        	// Lấy danh sách tất cả các bàn từ database qua BO
            List<Long> tablesList = TablesBO.getAllTablesId();

            request.setAttribute("tablesList", tablesList);
        	// Lấy danh sách tất cả các booking từ database qua BO
            List<Booking> bookingsList = BookingBO.getAllBookings();
         // Đưa danh sách booking vào request
            request.setAttribute("bookingsList", bookingsList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateBooking.jsp");
            dispatcher.forward(request, response);
        } else if (method.equalsIgnoreCase("POST")) {
            long id = Long.parseLong(request.getParameter("id"));
            long userId = Long.parseLong(request.getParameter("user_id"));
            long tableId = Long.parseLong(request.getParameter("table_id"));
            long statusId = Long.parseLong(request.getParameter("status_id"));
            String date = request.getParameter("date");

            Booking booking = new Booking();
            booking.setId(id);
            booking.setUser_id(userId);
            booking.setTable_id(tableId);
            booking.setStatus_id(statusId);
            booking.setDate(java.sql.Date.valueOf(date));

            BookingBO.update(booking);
            response.sendRedirect("BookingController?action=index");
        }
    }

    // Xóa booking
    private void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String method = request.getMethod();

        if (method.equalsIgnoreCase("GET")) {
        	// Lấy danh sách tất cả các booking từ database qua BO
            List<Booking> bookingsList = BookingBO.getAllBookings();
         // Đưa danh sách booking vào request
            request.setAttribute("bookingsList", bookingsList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("DeleteBooking.jsp");
            dispatcher.forward(request, response);
        } else if (method.equalsIgnoreCase("POST")) {
        	long bookingId = Long.parseLong(request.getParameter("id"));
            BookingBO.delete(bookingId);
            response.sendRedirect("BookingController?action=index");
        }
    }

 // Xem danh sách booking
    private void view(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy danh sách tất cả các booking từ database qua BO
        List<Booking> bookingsList = BookingBO.getAllBookings();

        // Kiểm tra xem danh sách booking có trống không
        if (bookingsList != null && !bookingsList.isEmpty()) {
            // Đưa danh sách booking vào request
            request.setAttribute("bookingsList", bookingsList);

            // Chuyển đến trang ViewBookings.jsp để hiển thị danh sách booking
            RequestDispatcher dispatcher = request.getRequestDispatcher("ViewBooking.jsp");
            dispatcher.forward(request, response);
        } else {
            // Nếu không có booking, chuyển hướng về trang danh sách
            response.sendRedirect("BookingController?action=index");
        }
    }

    
    private void search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getMethod();

        if (method.equalsIgnoreCase("GET")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("SearchBooking.jsp");
            dispatcher.forward(request, response);
        } else if (method.equalsIgnoreCase("POST")) {
            String searchBy = request.getParameter("searchBy"); // Cột muốn tìm kiếm
            String keyword = request.getParameter("searchString"); // Từ khóa tìm kiếm

            List<Booking> bookingList = BookingBO.searchByCol(searchBy, keyword);
            request.setAttribute("bookingList", bookingList);

            RequestDispatcher dispatcher = request.getRequestDispatcher("SearchBooking.jsp");
            dispatcher.forward(request, response);
        }
    }

}
