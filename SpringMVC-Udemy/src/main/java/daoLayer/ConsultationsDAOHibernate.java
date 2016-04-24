package daoLayer;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

import domainLayer.Consultation;
import domainLayer.Patient;
import domainLayer.User;

@Component("consultsDAO")
@Repository //Asta il folosesti sa transformi Hibernate Ex in Spring Ex.
@Transactional
public class ConsultationsDAOHibernate implements ConsultationsDAO{
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session(){
		return sessionFactory.getCurrentSession();
	}  
	
	@Override
	@Transactional
	public void  saveOrUpdateConsultation(Consultation consultation){
		session().saveOrUpdate(consultation);
	}

	@Override
	public boolean checkIfExists(int id){
		Criteria crit = session().createCriteria(Consultation.class);
		crit.add(Restrictions.idEq(id)); // Asta e cand faci query pe coloana care e PK
		Consultation consult=(Consultation) crit.uniqueResult();
		return consult != null;
	}

	@Override
	public List<Consultation>getAllConsultations(){
		return session().createQuery("from Consultation").list();
	
	}

	@Override
	public Consultation getConsultation(int id){
		Criteria crit = session().createCriteria(Consultation.class);
		crit.add(Restrictions.idEq(id)); 
		Consultation consult=(Consultation) crit.uniqueResult();
		return consult;
	}

	@Override
	public void deleteConsultation(int id) {
		Consultation consult = getConsultation(id);
		session().delete(consult);
		
	}

	@Override
	public List<Consultation> getConsultationsForPatient(String personalNumericNumber) {
		Criteria crit=session().createCriteria(Consultation.class);
		crit.createAlias("patient", "patient");

		crit.add(Restrictions.eq("patient.personalNumericCode",personalNumericNumber));
		List<Consultation> consults = crit.list();
		 
		 
		return consults.stream().filter(consult->consult.getDate().after(new Date())).collect(Collectors.toList());
		
	}

	@Override
	public List<Consultation> getPastConsultations(String personalNumericCode, Date date) {

		List<Consultation> consults = getConsultationsForPatient(personalNumericCode);
		
		return consults;
//		return consults.stream().filter(consult->consult.getDate().before(date)).collect(Collectors.toList());
	}

	@Override
	public List<Consultation> getConsultationsForDoctor(String doctorName) {
		
		Criteria crit=session().createCriteria(Consultation.class);
		crit.add(Restrictions.eq("doctorName",doctorName));
		 
		return crit.list();
		
	}

}
