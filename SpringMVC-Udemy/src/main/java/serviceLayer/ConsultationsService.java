package serviceLayer;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import daoLayer.ConsultationsDAO;
import domainLayer.Consultation;
import domainLayer.Patient;
import domainLayer.User;

@Service("consultsService")
public class ConsultationsService {

	@Autowired
	private ConsultationsDAO consultsDAO;
	
	public void saveOrUpdateConsultation(Consultation consultation){
		consultsDAO.saveOrUpdateConsultation(consultation);
	}

	public boolean checkIfExists(int id){
		return consultsDAO.checkIfExists(id);
	}

	public List<Consultation> getAllConsultations(){
		 List<Consultation> allConsultations = consultsDAO.getAllConsultations();
		 
		 return allConsultations;
	}

	public Consultation getConsultation(int id){
		return consultsDAO.getConsultation(id);
	}

	public void deleteConsultation(int id){
		consultsDAO.deleteConsultation(id);
	}
	
	public List<Consultation> getConsultationsForPatient(String personalNumericNumber){
		
		 List<Consultation> consultList = consultsDAO.getConsultationsForPatient(personalNumericNumber);
		 
		 Collections.sort(consultList, new Comparator<Consultation>() {
			    public int compare(Consultation c1, Consultation c2) {
			        return c1.getDate().compareTo(c2.getDate());
			    }
			});
		 
		return consultList;
	}

	public List<Consultation> getPastConsultations(String personalNumericCode, Date date) {
		
		List<Consultation> consultList = consultsDAO.getPastConsultations(personalNumericCode,date);
		 
		 Collections.sort(consultList, new Comparator<Consultation>() {
			    public int compare(Consultation c1, Consultation c2) {
			        return c2.getDate().compareTo(c1.getDate());
			    }
			});
		 
		return consultList;
	}

	public List<Consultation> getConsultationsForDoctor(String doctorName) {
		return consultsDAO.getConsultationsForDoctor(doctorName);
		
	}

}