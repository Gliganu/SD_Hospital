package serviceLayer;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import daoLayer.ConsultationsDAO;
import daoLayer.UsersDAO;
import domainLayer.Consultation;
import domainLayer.User;

@Service("usersService")
public class UsersService {

	@Autowired
	private UsersDAO usersDao;
	
	@Autowired
	private ConsultationsDAO consultsDAO;

	
	public void saveUser(User user){
		usersDao.saveUser(user);
	}


	public boolean exists(String username) {
		return usersDao.exists(username);
	}


	public List<User> getAllUsers() {
		return usersDao.getAllUsers();
	}
	
	public List<User> getAllDoctors() {
		return usersDao.getAllDoctors();
	}
	
	public User getUser(String username){
		return usersDao.getUser(username);
	}
	
	public User getUserByName(String name){
		return usersDao.getUserByName(name);
		
	}


	public void deleteUser(String username) {
		
		User user = getUser(username);
		if("ROLE_DOCTOR".equals(user.getAuthority())){
			List<Consultation> consultationsForDoctor = consultsDAO.getConsultationsForDoctor(user.getName());
			
			for(Consultation consult : consultationsForDoctor){
				consultsDAO.deleteConsultation(consult.getId());
			}
			
		}
		
		usersDao.deleteUser(username);
	}


	public void updateUser(User user) {
		usersDao.updateUser(user);
	}


	public boolean isDoctorAvailable(String doctorName, int lengthInMinutes, Date consultDate) {
		long newConsultDateStart = consultDate.getTime();
		long newConsultDateEnd = newConsultDateStart + lengthInMinutes*60*1000;
		
		List<Consultation> consultationsForDoctor = consultsDAO.getConsultationsForDoctor(doctorName);
		
		for(Consultation consultation: consultationsForDoctor){
			
			long oldConsultDateStart = consultation.getDate().getTime();
			long oldConsultDateEnd = oldConsultDateStart + lengthInMinutes*60*1000;
			
			if(oldConsultDateStart <= newConsultDateStart && oldConsultDateEnd >= newConsultDateStart){
				return false;
			}
			
			if(oldConsultDateStart <= newConsultDateEnd && oldConsultDateEnd >= newConsultDateEnd ){
				return false;
			}
			
		}
		
		return true;
	}


}
