package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import daoLayer.UsersDAO;
import domainLayer.User;
import serviceLayer.ServiceUtils;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"file:src/main/java/config/security-context.xml",
		"file:src/test/java/testConfig/datasource.xml" }) 
@RunWith(SpringJUnit4ClassRunner.class)
public class UsersDAOTests {

	@Autowired
	private UsersDAO usersDAO;

	@Autowired
	private DataSource dataSource;
	
	User user1 = new User("secre", "test", "Mihai Pop", ServiceUtils.getRandomCNP(), "Dorobantilor 109", "ROLE_SECRETARY", true);
	User user2 = new User("admin", "test", "Andreea Muresan", ServiceUtils.getRandomCNP(), "Ceahlau 14", "ROLE_ADMIN", true);
	User user3 = new User("doctor", "test", "Bogdan Gliga", ServiceUtils.getRandomCNP(), "Giulesti 10", "ROLE_DOCTOR", true);
//	User user4 = new User("administrator", "test", "Flaviu Stoican", ServiceUtils.getRandomCNP(), "Pitestu 140", "ROLE_ADMIN", true);
//	User user5 = new User("mircea", "test", "Mircea Nitu", ServiceUtils.getRandomCNP(), "Mihai Viteazu 12", "ROLE_USER", true);
//	User user6 = new User("oana", "test", "Oana Blaga", ServiceUtils.getRandomCNP(), "Fanfara 2", "ROLE_USER", true);
//	User user7 = new User("cristi", "test", "Cristi Mincea", ServiceUtils.getRandomCNP(), "Mircea cel Batran 103", "ROLE_USER", true);
//	User user8 = new User("andreea", "test", "Andreea Davidescu", ServiceUtils.getRandomCNP(), "Carmen 130", "ROLE_USER", true);
//	User user9 = new User("stefan", "test", "Stefan Fodor", ServiceUtils.getRandomCNP(), "Dunarii 210", "ROLE_USER", true);
//	User user10 = new User("iulia", "test", "Iulia Lazar", ServiceUtils.getRandomCNP(), "Calarasi 40", "ROLE_USER", true);


	@Before
	public void init() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		jdbcTemplate.execute("DELETE FROM users");
	}

	@Test
	public void testCreateRetrive(){
		usersDAO.saveUser(user1);
		usersDAO.saveUser(user2);
		usersDAO.saveUser(user3);
//		usersDAO.saveUser(user4);
//		usersDAO.saveUser(user5);
//		usersDAO.saveUser(user6);
//		usersDAO.saveUser(user7);
//		usersDAO.saveUser(user8);
//		usersDAO.saveUser(user9);
//		usersDAO.saveUser(user10);
//		
//		List<User> userList2= usersDAO.getAllUsers();
//		assertEquals("Ten users should have been created and retrieved", 10, userList2.size());
	}
	
	@Test
	public void testExists(){
		usersDAO.saveUser(user1);
		usersDAO.saveUser(user2);
		
		assertTrue("User should exist", usersDAO.exists(user1.getUsername()));
		assertTrue("User should exist", usersDAO.exists(user2.getUsername()));

	}
	
	@Test
	public void testUpdate(){
		usersDAO.saveUser(user1);
		
		user1.setName("nameee");
		
		usersDAO.updateUser(user1);
		
		User retrieved = usersDAO.getUser(user1.getUsername());
		
		assertTrue(retrieved.getName().equals("nameee"));
	}

}