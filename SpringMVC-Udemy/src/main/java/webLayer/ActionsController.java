package webLayer;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import daoLayer.ConsultationsDAO;
import domainLayer.Consultation;
import domainLayer.Patient;
import domainLayer.User;
import serviceLayer.ConsultationsService;
import serviceLayer.PatientsService;
import serviceLayer.UsersService;

@Controller
public class ActionsController {

	public static String ADD_EDIT_PATIENT_PAGE = "addEditPatient";
	public static String ADD_EDIT_CONSULT_PAGE = "addEditConsult";
	public static String UPDATE_PATIENT_PAGE = "updatePatient";
	public static String PATIENTS_PAGE = "patients";
	public static String CONSULTS_PAGE = "consultations";

	@Autowired
	PatientsService patientsService;
	
	@Autowired
	private UsersService usersService;

	@Autowired
	private ConsultationsService consultsService;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showHomePage(Model model) {
		
		List<Patient> patientList = patientsService.getAllPatients();
		
		model.addAttribute("patients",patientList);
		
		return PATIENTS_PAGE;
	}
	
	@RequestMapping(value = "/deletePatient", method = RequestMethod.GET)
	public String deleteBook(@RequestParam(value = "id", required = true) String personalNumericCode, Model model, Principal principal) {

		patientsService.deletePatient(personalNumericCode);
		
		return showHomeWithMessage(model, "Successfully deleted patient");
	}

	@RequestMapping(value = "/addPatient", method = RequestMethod.GET)
	public String addPatient(Model model, Principal principal) {

		Patient patient = new Patient();

		model.addAttribute("patient", patient);

		return ADD_EDIT_PATIENT_PAGE;
	}
	
	@RequestMapping(value = "/addPatient", method = RequestMethod.POST)
	public String processAddPatient(@Valid Patient patient, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return ADD_EDIT_PATIENT_PAGE;
		} else {
				
			if(patient.getDateOfBirth().after(new Date())){
				result.rejectValue("dateOfBirth", "Future.patient.dateOfBirth");
				return ADD_EDIT_PATIENT_PAGE;
			}
			
			Patient existingPatient = patientsService.getPatient(patient.getPersonalNumericCode());
			if( existingPatient != null && !existingPatient.getName().equals(patient.getName())){
				result.rejectValue("personalNumericCode", "Exists.patient.personalNumericCode");
				return ADD_EDIT_PATIENT_PAGE;
			}
			
			patientsService.savePatient(patient);

			return showHomeWithMessage(model, "Successfully updated patient info");
		}

	}
	
	@RequestMapping(value = "/updatePatient", method = RequestMethod.GET)
	public String updatePatient(@RequestParam("id") String personalNumericCode, Model model, Principal principal) {

		Patient patient = patientsService.getPatient(personalNumericCode);

		model.addAttribute("patient", patient);

		return UPDATE_PATIENT_PAGE;
	}

	
	@RequestMapping(value = "/updatePatient", method = RequestMethod.POST)
	public String processUpdatePatient(@Valid Patient patient, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return UPDATE_PATIENT_PAGE;
		} else {
				
			if(patient.getDateOfBirth().after(new Date())){
				result.rejectValue("dateOfBirth", "Future.patient.dateOfBirth");
				return UPDATE_PATIENT_PAGE;
			}
			patientsService.savePatient(patient);

			return showHomeWithMessage(model, "Successfully updated patient info");
		}

	}
	
	
	
	private String showHomeWithMessage(Model model, String message){
		
		model.addAttribute("successMsg", message);
		return showHomePage(model);
		
	}
	
//	@InitBinder
//	protected void initBinder(WebDataBinder binder) {
//	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
//	}
}
