package Model.BO;

import java.util.List;

import Model.BEAN.Role;
import Model.DAO.RoleDAO;

public class RoleBO {
private RoleDAO dao = new RoleDAO();
	
	public boolean create(Role Role) {
		return this.dao.createNewRole(Role);
	}
	
	public boolean delete(long id) {
		if(!this.dao.checkExist(id)) {
			return false;
		}
		return this.dao.deleteRole(id);
	}
	
	public boolean update(Role Role) {
		if(!this.dao.checkExist(Role.getId())) {
			return false;
		}
		return this.dao.updateRole(Role);
	}
	
	public List<Role> getAllRole(){
		return this.dao.getAllRole();
	}
	
	public Role getRoleById(long id) {
		if(!this.dao.checkExist(id)) {
			return null;
		}
		return this.dao.getRoleById(id);
	}
}
