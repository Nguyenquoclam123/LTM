package Model.BO;

import java.util.List;

import Model.BEAN.Status;
import Model.DAO.StatusDAO;

public class StatusBO {
	private StatusDAO dao = new StatusDAO();
	
	public boolean create(Status status) {
		return this.dao.createNewStatus(status);
	}
	
	public boolean delete(long id) {
		if(!this.dao.checkExist(id)) {
			return false;
		}
		return this.dao.deleteStatus(id);
	}
	
	public boolean update(Status status) {
		if(!this.dao.checkExist(status.getId())) {
			return false;
		}
		return this.dao.updateStatus(status);
	}
	
	public List<Status> getAllStatus(){
		return this.dao.getAllStatus();
	}
	
	public Status getStatusById(long id) {
		if(!this.dao.checkExist(id)) {
			return null;
		}
		return this.dao.getStatusById(id);
	}
}
