package Model.BO;

import java.util.List;

import Model.BEAN.Tables;
import Model.DAO.TablesDAO;

public class TablesBO {
    private static TablesDAO dao = new TablesDAO(); // Sử dụng biến static cho DAO

    // Tạo mới một table
    public static boolean create(Tables table) {
        return dao.createNewTable(table);
    }

    // Xóa một table dựa trên ID
    public static boolean delete(long id) {
        if (!dao.checkExist(id)) {
            return false;
        }
        return dao.deleteTable(id);
    }

    // Cập nhật thông tin một table
    public static boolean update(Tables table) {
        if (!dao.checkExist(table.getId())) {
            return false;
        }
        return dao.updateTable(table);
    }

    // Lấy danh sách tất cả table
    public static List<Tables> getAllTables() {
        return dao.getAllTables();
    }

    // Lấy thông tin một table dựa trên ID
    public static Tables getTableById(long id) {
        if (!dao.checkExist(id)) {
            return null;
        }
        return dao.getTableById(id);
    }
}
