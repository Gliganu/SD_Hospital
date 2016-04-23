package serviceLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import daoLayer.UsersDAO;
import domainLayer.User;

@Service("usersService")
public class UsersService {

	@Autowired
	private UsersDAO usersDao;
	
	
	public void createUser(User user){
		usersDao.createUser(user);
	}


	public boolean exists(String username) {
		return usersDao.exists(username);
	}


	@Secured("ROLE_ADMIN")
	public List<User> getAllUsers() {
		return usersDao.getAllUsers();
	}
	
	
	
	public User getUser(String username){
		return usersDao.getUser(username);
		
	}


	


}
