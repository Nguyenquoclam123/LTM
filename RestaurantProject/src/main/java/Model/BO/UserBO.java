package Model.BO;

import java.util.List;

import Model.BEAN.User;
import Model.DAO.UserDAO;

public class UserBO {
	private UserDAO dao = new UserDAO();
	
	public boolean checkLogin(String username,String password) {
		return this.dao.checkLogin(username, password);
	}
	
	public boolean create(User user) {
		return this.dao.createNewUser(user);
	}
	
	public boolean delete(long id) {
		if(!this.dao.checkExist(id)) {
			return false;
		}
		return this.dao.deleteUser(id);
	}
	
	public boolean update(User user) {
		if(!this.dao.checkExist(user.getId())) {
			return false;
		}
		return this.dao.updateUser(user);
	}
	
	public User getUserById(long id) {
		return this.dao.getUserById(id);
	}
	
	public List<User> getAllUser(){
		return this.dao.getAllUser();
	}
	
	public User getUserByUsername(String u) {
		return this.dao.getUserByUsername(u);
	}
}
