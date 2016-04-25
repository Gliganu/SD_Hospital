package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
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

import daoLayer.ConsultationsDAO;
import daoLayer.PatientsDAO;
import daoLayer.UsersDAO;
import domainLayer.Consultation;
import domainLayer.Patient;
import domainLayer.User;
import serviceLayer.ServiceUtils;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"file:src/main/java/config/security-context.xml",
		"file:src/test/java/testConfig/datasource.xml" }) 
@RunWith(SpringJUnit4ClassRunner.class)
public class ConsultationsDAOTests {

	@Autowired
	private UsersDAO usersDAO;
	
	@Autowired
	private PatientsDAO patientsDAO;

	@Autowired
	private ConsultationsDAO consultsDAO;
	
	@Autowired
	private DataSource dataSource;
	
	User s1 = new User("secre", "test", "Mihai Pop", ServiceUtils.getRandomCNP(), "Dorobantilor 109", "ROLE_SECRETARY", true);
	User a1 = new User("admin", "test", "Andreea Muresan", ServiceUtils.getRandomCNP(), "Ceahlau 14", "ROLE_ADMIN", true);
	User d1 = new User("doctor1", "test", "Dr.Mircea", ServiceUtils.getRandomCNP(), "Giulesti 10", "ROLE_DOCTOR", true);
	User d2 = new User("doctor2", "test", "Dr.Stefan", ServiceUtils.getRandomCNP(), "Giulesti 10", "ROLE_DOCTOR", true);
	User d3 = new User("doctor3", "test", "Dr.Andrada", ServiceUtils.getRandomCNP(), "Giulesti 10", "ROLE_DOCTOR", true);

	Patient p1 = new Patient("Bogdan", ServiceUtils.getRandomCNP(), new Date(),  "Dorobantilor 109");
	Patient p2 = new Patient("Andrei", ServiceUtils.getRandomCNP(), new Date(),  "Dorobantilor 109");
	Patient p3 = new Patient("Florin", ServiceUtils.getRandomCNP(), new Date(),  "Dorobantilor 109");
	Patient p4 = new Patient("Mircea", ServiceUtils.getRandomCNP(), new Date(),  "Dorobantilor 109");

	@Before
	public void init() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		jdbcTemplate.execute("DELETE FROM consultations");
		jdbcTemplate.execute("DELETE FROM users");
		jdbcTemplate.execute("DELETE FROM patients");
	}

	@Test
	public void testCreateRetrive(){
		usersDAO.saveUser(s1);
		usersDAO.saveUser(a1);
		usersDAO.saveUser(d1);
		usersDAO.saveUser(d2);
		usersDAO.saveUser(d3);
		
		patientsDAO.saveOrUpdatePatient(p1);
		patientsDAO.saveOrUpdatePatient(p2);
		patientsDAO.saveOrUpdatePatient(p3);
		patientsDAO.saveOrUpdatePatient(p4);
			
		List<Patient> patientList = new ArrayList<>();
		patientList.add(p1);
		patientList.add(p2);
		patientList.add(p3);
		
		List<User> doctorList = new ArrayList<>();
		doctorList.add(d1);
		doctorList.add(d2);
		doctorList.add(d3);
		
		for(Patient patient: patientList){
			
			for(int i=0;i<5;i++){
				Consultation c1 = new Consultation(new Date(System.currentTimeMillis() - (i+1)*24*60*60*1000),patient,doctorList.get(i%3),"asd asdas dasdasdasdasda",30 + i*10);
				Consultation c2 = new Consultation(new Date(System.currentTimeMillis() + (i+1)*24*60*60*1000),patient,doctorList.get(i%3),"asd asdas dasdasdasdasda",30 + i*10);
				consultsDAO.saveOrUpdateConsultation(c1);
				consultsDAO.saveOrUpdateConsultation(c2);
			}
			
		}
		
		List<Consultation> l1 = consultsDAO.getConsultationsForPatient(p1.getPersonalNumericCode());
		List<Consultation> l2 = consultsDAO.getConsultationsForPatient(p2.getPersonalNumericCode());
		List<Consultation> l3 = consultsDAO.getConsultationsForPatient(p3.getPersonalNumericCode());
		
		assertEquals(l1.size(), 5);
		assertEquals(l2.size(), 5);
		assertEquals(l3.size(), 5);
		
	}
	
	
}