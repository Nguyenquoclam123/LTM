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

import Model.BEAN.Tables;
import Model.BO.TablesBO;


@WebServlet("/TablesController")
public class TablesController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public TablesController() {
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
            case "search":
                search(request, response);
                break;
            case "view":  // Thêm xử lý cho hành động "view"
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
            case "search":
                search(request, response);
                break;
            default:
                response.sendRedirect("TablesController?action=index");
                break;
        }
    }

    // Liệt kê danh sách bàn
    private void index(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Tables> tablesList = TablesBO.getAllTables();
        request.setAttribute("tablesList", tablesList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Index_Tables.jsp");
        dispatcher.forward(request, response);
    }

    // Thêm mới bàn
    private void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getMethod();

        if (method.equalsIgnoreCase("GET")) {
        	// Lấy danh sách tất cả các bàn từ database qua BO
            List<Tables> tablesList = TablesBO.getAllTables();

            request.setAttribute("tablesList", tablesList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("CreateTables.jsp");
            dispatcher.forward(request, response);
        } else if (method.equalsIgnoreCase("POST")) {
            String tableNumber = request.getParameter("number").trim();
            int statusId = Integer.parseInt(request.getParameter("status_id").trim());

            Tables table = new Tables();
            table.setNumber(tableNumber);
            table.setStatus_id(statusId);

            TablesBO.create(table);
            response.sendRedirect("TablesController?action=index");
        }
    }

    // Sửa thông tin bàn
    private void edit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getMethod();

        if (method.equalsIgnoreCase("GET")) {
        	// Lấy danh sách tất cả các bàn từ database qua BO
            List<Tables> tablesList = TablesBO.getAllTables();

            request.setAttribute("tablesList", tablesList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateTables.jsp");
            dispatcher.forward(request, response);
        } else if (method.equalsIgnoreCase("POST")) {
            long id = Long.parseLong(request.getParameter("id"));
            String tableNumber = request.getParameter("number").trim();
            int statusId = Integer.parseInt(request.getParameter("status_id").trim());

            Tables table = new Tables();
            table.setId(id);
            table.setNumber(tableNumber);
            table.setStatus_id(statusId);

            TablesBO.update(table);
            response.sendRedirect("TablesController?action=index");
        }
    }

    // Xóa bàn
    private void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String method = request.getMethod();

        if (method.equalsIgnoreCase("GET")) {
        	// Lấy danh sách tất cả các bàn từ database qua BO
            List<Tables> tablesList = TablesBO.getAllTables();

            request.setAttribute("tablesList", tablesList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("DeleteTables.jsp");
            dispatcher.forward(request, response);
        } else if (method.equalsIgnoreCase("POST")) {
            long id = Long.parseLong(request.getParameter("id"));
            TablesBO.delete(id);
            response.sendRedirect("TablesController?action=index");
        }    
        }

    // Tìm kiếm bàn
    private void search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String method = request.getMethod();

        if (method.equalsIgnoreCase("GET")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("SearchTables.jsp");
            dispatcher.forward(request, response);
        } else if (method.equalsIgnoreCase("POST")) {
            String searchBy = request.getParameter("searchBy");
            String searchString = request.getParameter("searchString");
            String statusId = request.getParameter("status_id");

            List<Tables> tablesList;

            if ("status_id".equals(searchBy) && statusId != null && !statusId.isEmpty()) {
                // Gọi hàm tìm kiếm theo trạng thái
                tablesList = TablesBO.searchByStatus(Integer.parseInt(statusId));
            } else {
                // Gọi hàm tìm kiếm theo cột thông thường
                tablesList = TablesBO.searchByCol(searchBy, searchString);
            }

            request.setAttribute("tablesList", tablesList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("SearchTables.jsp");
            dispatcher.forward(request, response);
        }
    }

    
 // Xem danh sách bàn
    private void view(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	List<Tables> tablesList = TablesBO.getAllTables();  // Lấy danh sách tất cả nhân viên

        // Kiểm tra xem có nhân viên nào hay không
        if (tablesList != null && !tablesList.isEmpty()) {
            request.setAttribute("tablesList", tablesList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("ViewTables.jsp");  // Chuyển đến trang View.jsp
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("TablesController?action=index");  // Nếu không có nhân viên, quay lại danh sách
        }
    }


}
