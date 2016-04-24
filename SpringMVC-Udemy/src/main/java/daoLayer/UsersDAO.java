package daoLayer;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import domainLayer.User;

public interface UsersDAO {

	void saveUser(User user);

	boolean exists(String username);

	List<User> getAllUsers();
	
	User getUser(String username);
	
	void deleteAllUsers();
	
	void updateUser(User user);

	List<User> getAllDoctors();

	User getUserByName(String name);

	void deleteUser(String username);
}