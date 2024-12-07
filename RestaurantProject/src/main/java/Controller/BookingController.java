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
import Model.BO.BookingBO;

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
        RequestDispatcher dispatcher = request.getRequestDispatcher("Booking/Index.jsp");
        dispatcher.forward(request, response);
    }

    // Tạo mới booking
    private void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getMethod();

        if (method.equalsIgnoreCase("GET")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("Booking/Create.jsp");
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
            long bookingId = Long.parseLong(request.getParameter("id"));
            Booking booking = BookingBO.getBookingById(bookingId);

            request.setAttribute("booking", booking);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Booking/Edit.jsp");
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
        long bookingId = Long.parseLong(request.getParameter("id"));
        BookingBO.delete(bookingId);
        response.sendRedirect("BookingController?action=index");
    }

    // Xem chi tiết booking
    private void view(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long bookingId = Long.parseLong(request.getParameter("id"));
        Booking booking = BookingBO.getBookingById(bookingId);

        request.setAttribute("booking", booking);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Booking/View.jsp");
        dispatcher.forward(request, response);
    }
}
