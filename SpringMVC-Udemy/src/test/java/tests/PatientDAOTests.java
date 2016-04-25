package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

import daoLayer.PatientsDAO;
import daoLayer.UsersDAO;
import domainLayer.Patient;
import domainLayer.User;
import serviceLayer.ServiceUtils;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"file:src/main/java/config/security-context.xml",
		"file:src/test/java/testConfig/datasource.xml" }) 
@RunWith(SpringJUnit4ClassRunner.class)
public class PatientDAOTests {

	@Autowired
	private PatientsDAO patientsDAO;

	@Autowired
	private DataSource dataSource;
	
	Patient p1 = new Patient("Bogdan", ServiceUtils.getRandomCNP(), new Date(),  "Dorobantilor 109");
	Patient p2 = new Patient("Andrei", ServiceUtils.getRandomCNP(), new Date(),  "Dorobantilor 109");
	Patient p3 = new Patient("Florin", ServiceUtils.getRandomCNP(), new Date(),  "Dorobantilor 109");
	Patient p4 = new Patient("Mircea", ServiceUtils.getRandomCNP(), new Date(),  "Dorobantilor 109");
	Patient p5 = new Patient("Andra", ServiceUtils.getRandomCNP(), new Date(),  "Dorobantilor 109");
	Patient p6 = new Patient("Razvan", ServiceUtils.getRandomCNP(), new Date(),  "Dorobantilor 109");
	Patient p7 = new Patient("Andreea", ServiceUtils.getRandomCNP(), new Date(),  "Dorobantilor 109");


	@Before
	public void init() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		jdbcTemplate.execute("DELETE FROM patients");
	}

	@Test
	public void testCreateRetrive(){
		patientsDAO.saveOrUpdatePatient(p1);
		patientsDAO.saveOrUpdatePatient(p2);
		patientsDAO.saveOrUpdatePatient(p3);
		patientsDAO.saveOrUpdatePatient(p4);
		patientsDAO.saveOrUpdatePatient(p5);
		patientsDAO.saveOrUpdatePatient(p6);
		patientsDAO.saveOrUpdatePatient(p7);
		
		List<Patient> patientList= patientsDAO.getAllPatients();
		assertEquals("Seven users should have been created and retrieved", 7, patientList.size());
	}
	
	@Test
	public void testExists(){
		patientsDAO.saveOrUpdatePatient(p1);
		patientsDAO.saveOrUpdatePatient(p2);
		
		assertTrue("Patient should exist", patientsDAO.checkIfExists(p1.getPersonalNumericCode()));
		assertTrue("Patient should exist", patientsDAO.checkIfExists(p2.getPersonalNumericCode()));

	}
	
	@Test
	public void testUpdate(){
		patientsDAO.saveOrUpdatePatient(p1);
		
		p1.setName("nameee");
		
		patientsDAO.saveOrUpdatePatient(p1);
		
		Patient retrieved = patientsDAO.getPatient(p1.getPersonalNumericCode());
		
		assertTrue(retrieved.getName().equals("nameee"));
	}
	
	
}