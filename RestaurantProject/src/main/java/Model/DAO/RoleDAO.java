package Model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.BEAN.Role;

public class RoleDAO {
	public boolean checkRoleName(String name) {
		for(Role role : getAllRole()) {
			if(role.getName().equals(name))
				return true;
		}
		return false;
	}
	
	public boolean checkExist(long id) {
		for(Role role : getAllRole()) {
			if(role.getId() == id)
				return true;
		}
		return false;
	}
	
	public boolean createNewRole(Role role) {
		if(checkRoleName(role.getName())) {
			return false;
		}
		try {
	        String url = "jdbc:mysql://localhost:3306/cuoikiltm"; 
	        String user = "root";  
	        String password = ""; 
	        Class.forName("com.mysql.jdbc.Driver");
	        Connection conn = DriverManager.getConnection(url, user, password);
	        Statement stmt = conn.createStatement();
	        String query = "Insert into role(name) values('"+role.getName()+"')";
	        int result = stmt.executeUpdate(query);
            if (result > 0) {
                return true;
            }
	    } catch (ClassNotFoundException e) {
	        System.out.println("Không tìm thấy MySQL JDBC Driver: " + e.getMessage());
	    } catch (SQLException e) {
	        System.out.println("Lỗi: " + e.getMessage());
	    }
		return false;
	}
	
	public List<Role> getAllRole(){
		List<Role> list = new ArrayList<Role>();
		try {
	        String url = "jdbc:mysql://localhost:3306/cuoikiltm"; 
	        String user = "root";  
	        String password = ""; 
	        Class.forName("com.mysql.jdbc.Driver");
	
	        Connection conn = DriverManager.getConnection(url, user, password);
	
	        Statement stmt = conn.createStatement();
	
	        String query = "SELECT * FROM role";
	        ResultSet rs = stmt.executeQuery(query);
	
	        while (rs.next()) {
	            Role role = new Role();
	            role.setId(rs.getLong("id"));
	            role.setName(rs.getString("name"));
	            list.add(role);
	        }
			
	        rs.close();
	        conn.close();
	    } catch (ClassNotFoundException e) {
	        System.out.println("Không tìm thấy MySQL JDBC Driver: " + e.getMessage());
	    } catch (SQLException e) {
	        System.out.println("Lỗi: " + e.getMessage());
	    }
		return list;
	}
	
	public Role getRoleById(long id){
		Role role = new Role();
		try {
	        String url = "jdbc:mysql://localhost:3306/cuoikiltm"; 
	        String user = "root";  
	        String password = ""; 
	        Class.forName("com.mysql.jdbc.Driver");
	
	        Connection conn = DriverManager.getConnection(url, user, password);
	
	        Statement stmt = conn.createStatement();
	
	        String query = "SELECT * FROM role where id = '"+id+"'";
	        ResultSet rs = stmt.executeQuery(query);
	
	        while (rs.next()) {
	            role.setId(rs.getLong("id"));
	            role.setName(rs.getString("name"));
	        }
			
	        rs.close();
	        conn.close();
	    } catch (ClassNotFoundException e) {
	        System.out.println("Không tìm thấy MySQL JDBC Driver: " + e.getMessage());
	    } catch (SQLException e) {
	        System.out.println("Lỗi: " + e.getMessage());
	    }
		return role != null ? role : null;
	}
	
	public boolean deleteRole(long id) {
		String query = "DELETE FROM Role WHERE id = ?";
	    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuoikiltm", "root", "");
	         PreparedStatement pstmt = conn.prepareStatement(query)) {

	        pstmt.setLong(1, id);

	        int result = pstmt.executeUpdate();
	        return result > 0;
	    } catch (SQLException e) {
	        System.out.println("Lỗi: " + e.getMessage());
	    }
	    return false;
	}
	
	public boolean updateRole(Role Role) {
		String query = "UPDATE Role SET name=? WHERE id = ?";
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuoikiltm", "root", "");
		     PreparedStatement pstmt = conn.prepareStatement(query)) {
		    pstmt.setString(1, Role.getName());
		    pstmt.setLong(2, Role.getId());
		   
		    int result = pstmt.executeUpdate();
		    return result > 0;
		} catch (SQLException e) {
		    System.out.println("Lỗi: " + e.getMessage());
		}
		return false;
	}
}
