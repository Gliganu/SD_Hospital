package daoLayer;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import domainLayer.Patient;
import domainLayer.User;

@Component("patientsDAO")
@Repository //Asta il folosesti sa transformi Hibernate Ex in Spring Ex.
@Transactional
public class PatientsDAOHibernate implements PatientsDAO{
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session(){
		return sessionFactory.getCurrentSession();
	}  
	
	@Override
	@Transactional
	public void saveOrUpdatePatient(Patient patient) {
		session().saveOrUpdate(patient);
	}

	@Override
	public boolean checkIfExists(String personalNumericCode) {
		Criteria crit = session().createCriteria(Patient.class);
		crit.add(Restrictions.idEq(personalNumericCode)); // Asta e cand faci query pe coloana care e PK
		Patient patient=(Patient) crit.uniqueResult();
		return patient != null;
	}

	@Override
	public List<Patient> getAllPatients() {
		return session().createQuery("from Patient").list();
	
	}

	@Override
	public Patient getPatient(String personalNumericCode) {
		Criteria crit = session().createCriteria(Patient.class);
		crit.add(Restrictions.idEq(personalNumericCode)); 
		Patient user=(Patient) crit.uniqueResult();
		return user;
	}

	@Override
	public void deletePatient(String personalNumericCode) {
		Patient patient = getPatient(personalNumericCode);
		session().delete(patient);
		
	}

}
