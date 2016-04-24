package serviceLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import daoLayer.PatientsDAO;
import daoLayer.UsersDAO;
import domainLayer.Patient;

@Service("patientsService")
public class PatientsService {

	@Autowired
	private PatientsDAO patientsDao;
	
	
	public void savePatient(Patient patient){
		patientsDao.saveOrUpdatePatient(patient);
	}


	public boolean checkIfExists(String personalNumericCode){
		return patientsDao.checkIfExists(personalNumericCode);
	}


	public  List<Patient> getAllPatients(){
		return patientsDao.getAllPatients();
	}
	
	public Patient getPatient(String personalNumericCode){
		return patientsDao.getPatient(personalNumericCode);
		
	}
	
	public void deletePatient(String personalNumericCode){
		patientsDao.deletePatient(personalNumericCode);
	}


}
