package daoLayer;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import domainLayer.Patient;
import domainLayer.User;

public interface PatientsDAO {

	public abstract void saveOrUpdatePatient(Patient patient);

	public abstract boolean checkIfExists(String personalNumericCode);

	public abstract List<Patient> getAllPatients();

	public abstract Patient getPatient(String personalNumericCode);

	public abstract void deletePatient(String personalNumericCode);

}