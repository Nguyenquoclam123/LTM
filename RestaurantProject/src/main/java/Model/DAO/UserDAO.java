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
import Model.BEAN.User;

public class UserDAO {
	public boolean checkLogin(String username, String password_) {
		int count =0 ;
		try {
	        String url = "jdbc:mysql://localhost:3306/cuoikiltm"; 
	        String user = "root";  
	        String password = ""; 
	        Class.forName("com.mysql.jdbc.Driver");
	
	        Connection conn = DriverManager.getConnection(url, user, password);
	
	        Statement stmt = conn.createStatement();
	
	        String query = "SELECT * FROM users where username = '"+username+"' and password = '"+password_+"'";
	        ResultSet rs = stmt.executeQuery(query);
	
	        while (rs.next()) {
	            count++;
	        }
	        rs.close();
	        conn.close();			
	    } catch (ClassNotFoundException e) {
	        System.out.println("Lỗi MySQL JDBC Driver: " + e.getMessage());
	    } catch (SQLException e) {
	        System.out.println("Lỗi: " + e.getMessage());
	    }
		return count !=0;
	}

	
	public boolean checkUsername(String name) {
		for(User User : getAllUser()) {
			if(User.getUsername().equals(name))
				return true;
		}
		return false;
	}
	
	public boolean checkExist(long id) {
		for(User User : getAllUser()) {
			if(User.getId() == id)
				return true;
		}
		return false;
	}
	
	public boolean createNewUser(User user) {
		if(checkUsername(user.getUsername())) {
			return false;
		}
		try {
	        String url = "jdbc:mysql://localhost:3306/cuoikiltm"; 
	        String username = "root";  
	        String password = ""; 
	        Class.forName("com.mysql.jdbc.Driver");
	        Connection conn = DriverManager.getConnection(url, username, password);
	        Statement stmt = conn.createStatement();
	        String query = "Insert into users(username,password,fullName,address,phonenumber,gender,age,role_id) values('"+user.getUsername()+"','"+user.getPassword()+"','"+user.getFullName()+"','"+user.getAddress()+"','"+user.getPhonenumber()+"','"+user.getAge()+"','"+user.getGender()+"','"+user.getRole().getId()+"')";
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
	
	public List<User> getAllUser(){
		List<User> list = new ArrayList<User>();
		try {
	        String url = "jdbc:mysql://localhost:3306/cuoikiltm"; 
	        String user = "root";  
	        String password = ""; 
	        Class.forName("com.mysql.jdbc.Driver");
	
	        Connection conn = DriverManager.getConnection(url, user, password);
	
	        Statement stmt = conn.createStatement();
	
	        String query = "SELECT * FROM users";
	        ResultSet rs = stmt.executeQuery(query);
	
	        while (rs.next()) {
	            User User = new User();
	            RoleDAO dao = new RoleDAO();
	            Role role = dao.getRoleById(rs.getInt("role_id"));
	            if(role != null) {
	            	User.setRole(role);
	            }
	            User.setId(rs.getLong("id"));
	            User.setPassword("********");
	            User.setUsername(rs.getString("username"));
	            User.setAge(rs.getInt("age"));
	            User.setFullName(rs.getString("fullName"));
	            User.setPhonenumber(rs.getString("phonenumber"));
	            User.setAddress(rs.getString("address"));
	            User.setGender(rs.getString("gender"));
	            list.add(User);
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
	
	public User getUserById(long id){
		User User = new User();
		try {
	        String url = "jdbc:mysql://localhost:3306/cuoikiltm"; 
	        String user = "root";  
	        String password = ""; 
	        Class.forName("com.mysql.jdbc.Driver");
	
	        Connection conn = DriverManager.getConnection(url, user, password);
	
	        Statement stmt = conn.createStatement();
	
	        String query = "SELECT * FROM users where id = '"+id+"'";
	        ResultSet rs = stmt.executeQuery(query);
	
	        while (rs.next()) {
	        	RoleDAO dao = new RoleDAO();
	            Role role = dao.getRoleById(rs.getInt("role_id"));
	            if(role != null) {
	            	User.setRole(role);
	            }
	            User.setId(rs.getLong("id"));
	            User.setPassword("********");
	            User.setUsername(rs.getString("username"));
	            User.setAge(rs.getInt("age"));
	            User.setFullName(rs.getString("fullName"));
	            User.setPhonenumber(rs.getString("phonenumber"));
	            User.setAddress(rs.getString("address"));
	            User.setGender(rs.getString("gender"));
	        }
			
	        rs.close();
	        conn.close();
	    } catch (ClassNotFoundException e) {
	        System.out.println("Không tìm thấy MySQL JDBC Driver: " + e.getMessage());
	    } catch (SQLException e) {
	        System.out.println("Lỗi: " + e.getMessage());
	    }
		return User != null ? User : null;
	}
	
	public User getUserByUsername(String username){
		User User = new User();
		try {
	        String url = "jdbc:mysql://localhost:3306/cuoikiltm"; 
	        String user = "root";  
	        String password = ""; 
	        Class.forName("com.mysql.jdbc.Driver");
	
	        Connection conn = DriverManager.getConnection(url, user, password);
	
	        Statement stmt = conn.createStatement();
	
	        String query = "SELECT * FROM users where username = '"+username+"'";
	        ResultSet rs = stmt.executeQuery(query);
	
	        while (rs.next()) {
	        	RoleDAO dao = new RoleDAO();
	            Role role = dao.getRoleById(rs.getInt("role_id"));
	            if(role != null) {
	            	User.setRole(role);
	            }
	            User.setId(rs.getLong("id"));
	            User.setPassword("********");
	            User.setUsername(rs.getString("username"));
	            User.setAge(rs.getInt("age"));
	            User.setFullName(rs.getString("fullName"));
	            User.setPhonenumber(rs.getString("phonenumber"));
	            User.setAddress(rs.getString("address"));
	            User.setGender(rs.getString("gender"));
	        }
			
	        rs.close();
	        conn.close();
	    } catch (ClassNotFoundException e) {
	        System.out.println("Không tìm thấy MySQL JDBC Driver: " + e.getMessage());
	    } catch (SQLException e) {
	        System.out.println("Lỗi: " + e.getMessage());
	    }
		return User != null ? User : null;
	}
	
	public boolean deleteUser(long id) {
		String query = "DELETE FROM users WHERE id = ?";
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
	
	
	public boolean updateUser(User User) {
		String query = "UPDATE users SET fullName=?,age=?,role_id=?,address=?,gender=?,phonenumber=? WHERE id = ?";
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuoikiltm", "root", "");
		    PreparedStatement pstmt = conn.prepareStatement(query)) {
		    pstmt.setString(1, User.getFullName());
		    pstmt.setInt(2, User.getAge());
		    pstmt.setLong(3, User.getRole().getId());
		    pstmt.setString(4, User.getAddress());
		    pstmt.setString(5, User.getGender());
		    pstmt.setString(6, User.getPhonenumber());
		    pstmt.setLong(7, User.getId());
		   
		    int result = pstmt.executeUpdate();
		    return result > 0;
		} catch (SQLException e) {
		    System.out.println("Lỗi: " + e.getMessage());
		}
		return false;
	}
}
