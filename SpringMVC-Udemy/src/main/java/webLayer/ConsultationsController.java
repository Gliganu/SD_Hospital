package webLayer;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import domainLayer.Alert;
import domainLayer.Consultation;
import domainLayer.Patient;
import domainLayer.Query;
import domainLayer.User;
import serviceLayer.ConsultationsService;
import serviceLayer.PatientsService;
import serviceLayer.UsersService;

@Controller
public class ConsultationsController {

	public static String ADD_EDIT_PATIENT_PAGE = "addEditPatient";
	public static String ADD_EDIT_CONSULT_PAGE = "addEditConsult";
	public static String PATIENTS_PAGE = "patients";
	public static String CONSULTS_PAGE = "consultations";
	public static String PAST_CONSULTS_PAGE = "pastConsultations";

	@Autowired
	PatientsService patientsService;
	
	@Autowired
	private UsersService usersService;

	@Autowired
	private ConsultationsService consultsService;
	
	
	@RequestMapping(value = "/consultations", method = RequestMethod.GET)
	public String showConsultations(@RequestParam(value = "id", required = true) String personalNumericCode,Model model) {
		
		List<Consultation> consultsList = consultsService.getConsultationsForPatient(personalNumericCode);
		
		model.addAttribute("consults",consultsList);
		
		model.addAttribute("patientCNP",personalNumericCode);
		
		return CONSULTS_PAGE;
	}
	
	@RequestMapping(value = "/deleteConsult", method = RequestMethod.GET)
	public String deleteConsult(@RequestParam(value = "id", required = true) int consultId,@RequestParam(value = "cnp", required = true) String  personalNumericCode, 
			Model model, Principal principal) {

		consultsService.deleteConsultation(consultId);
		
		return showConsultations(personalNumericCode, model);
	}

	@RequestMapping(value = "/addConsult", method = RequestMethod.GET)
	public String addConsult(@RequestParam(value = "id", required = true) String personalNumericCode, Model model, Principal principal) {
		
		Consultation consultation = new Consultation();
		consultation.setPatientPersonalNumericCode(personalNumericCode);
		model = addParamForEditConsult(model,consultation , "");
		
		return ADD_EDIT_CONSULT_PAGE;
	}
	
	@RequestMapping(value = "/updateConsult", method = RequestMethod.GET)
	public String updateConsult(@RequestParam(value = "id", required = true) int consultId, Model model, Principal principal) {

		Consultation consultation = consultsService.getConsultation(consultId);
		
		model = addParamForEditConsult(model, consultation, "");
		
		return ADD_EDIT_CONSULT_PAGE;
	}
	
	@RequestMapping(value = "/checkDoctorSchedule", method = RequestMethod.POST)
	public String checkDoctorSchedule(@Valid Query query,BindingResult result, Model model) {
		
		Consultation consultation = new Consultation();
		consultation.setPatientPersonalNumericCode(query.getPatientPersonalNumericCode());
		
		model = addParamForEditConsult(model,consultation , "");
		
		List<Consultation> consultationsForDoctor = consultsService.getConsultationsForDoctor(query.getDoctorName());
		model.addAttribute("consults", consultationsForDoctor);
		model.addAttribute("selectedDoctor", query.getDoctorName());
		
		return ADD_EDIT_CONSULT_PAGE;
	}
	
	@RequestMapping(value = "/updateConsult", method = RequestMethod.POST)
	public String processUpdateConsult(@Valid Consultation consult, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model = addParamForEditConsult(model, consult, "Invalid Length");
			return ADD_EDIT_CONSULT_PAGE;
		} else {
			
			boolean formValid = true;
			String invalidMessage = "";
			
			if (consult.getDate().before(new Date())) {
				invalidMessage = "Cannot select date from past";
				formValid = false;
			}
			
			if (!usersService.isDoctorAvailable(consult.getDoctorName(),consult.getLength(), consult.getDate())) {
				invalidMessage = "Doctor is not available at that time";
				formValid = false;
			}
			
			if(!formValid){
				model = addParamForEditConsult(model, consult, invalidMessage);
				return ADD_EDIT_CONSULT_PAGE;
			}
			
			
			String personalNumericCode = consult.getPatientPersonalNumericCode();
			
			Patient patient = patientsService.getPatient(personalNumericCode);
			consult.setPatient(patient);
			
			User doctor = usersService.getUserByName(consult.getDoctorName());
			consult.setUser(doctor);
			
			consultsService.saveOrUpdateConsultation(consult);
			
			return showConsultations(personalNumericCode,model);
		}

	}
	
	@RequestMapping(value = "/consultHistory", method = RequestMethod.GET)
	public String deleteConsult(@RequestParam(value = "id", required = true) String  personalNumericCode, 
			Model model, Principal principal) {

		List<Consultation> pastConsultList = consultsService.getPastConsultations(personalNumericCode,new Date());
		
		model.addAttribute("pastConsults",pastConsultList);
		
		return PAST_CONSULTS_PAGE;
	}
	
	private Model addParamForEditConsult(Model model, Consultation consult, String invalidMessage){
		model.addAttribute("consult", consult);
		
		List<User> doctorList = usersService.getAllDoctors();
		
		List<String> doctorNamesList = doctorList.stream()
		        .map(doctor -> doctor.getName()).collect(Collectors.toList());;
		
		model.addAttribute("doctorNames", doctorNamesList);
		model.addAttribute("errorMsg",invalidMessage);
		model.addAttribute("cnp", consult.getPatientPersonalNumericCode());
		model.addAttribute("query",  new Query());
		
		return model;
	}
	
	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Alert sendAlert(Principal principal) throws Exception {

		Consultation consultation = consultsService.getApprochingConsultation(principal.getName());
		
		if(consultation == null){
			return new Alert("");
		}
		
		return new Alert("You have a consultation with "+consultation.getPatient().getName()+" which starts now");
		
	}
	
}
