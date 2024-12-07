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
        RequestDispatcher dispatcher = request.getRequestDispatcher("Tables/Index.jsp");
        dispatcher.forward(request, response);
    }

    // Thêm mới bàn
    private void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getMethod();

        if (method.equalsIgnoreCase("GET")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("Tables/Create.jsp");
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
            long id = Long.parseLong(request.getParameter("id"));
            Tables table = TablesBO.getTableById(id);

            request.setAttribute("table", table);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Tables/Edit.jsp");
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
        long id = Long.parseLong(request.getParameter("id"));
        TablesBO.delete(id);
        response.sendRedirect("TablesController?action=index");
    }

    // Tìm kiếm bàn
    private void search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		/*
		 * String method = request.getMethod();
		 * 
		 * if (method.equalsIgnoreCase("GET")) { RequestDispatcher dispatcher =
		 * request.getRequestDispatcher("Tables/Search.jsp");
		 * dispatcher.forward(request, response); } else if
		 * (method.equalsIgnoreCase("POST")) { String searchBy =
		 * request.getParameter("searchBy"); String searchString =
		 * request.getParameter("searchString");
		 * 
		 * ArrayList<Tables> tablesList = TablesBO.search(searchBy, searchString);
		 * request.setAttribute("tablesList", tablesList); RequestDispatcher dispatcher
		 * = request.getRequestDispatcher("Tables/Search.jsp");
		 * dispatcher.forward(request, response); }
		 */
    }
}
