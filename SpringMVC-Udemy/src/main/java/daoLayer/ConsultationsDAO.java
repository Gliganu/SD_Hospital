package daoLayer;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import domainLayer.Consultation;
import domainLayer.Patient;
import domainLayer.User;

public interface ConsultationsDAO {

	public abstract void saveOrUpdateConsultation(Consultation consultation);

	public abstract boolean checkIfExists(int id);

	public abstract List<Consultation> getAllConsultations();

	public abstract Consultation getConsultation(int id);

	public abstract void deleteConsultation(int id);

	public abstract List<Consultation> getConsultationsForPatient(String personalNumericNumber);

	public abstract List<Consultation> getPastConsultations(String personalNumericCode, Date date);

	public abstract List<Consultation> getConsultationsForDoctor(String doctorName);

}