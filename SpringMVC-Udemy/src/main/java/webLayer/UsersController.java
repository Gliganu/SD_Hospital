package webLayer;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import domainLayer.Patient;
import domainLayer.User;
import serviceLayer.PatientsService;
import serviceLayer.UsersService;

@Controller
public class UsersController {

	private static String LOGIN_PAGE = "loginForm";
	private static String USERS_PAGE = "users";
	private static String CREATE_NEW_ACCOUNT_PAGE = "newAccountForm";
	private static String UPDATE_USER_INFO_PAGE = "updateUserInfo";
	private static String LOG_OUT_PAGE = "logOut";
	private static String ACCESS_DENIED_PAGE = "accessDenied";

	@Autowired
	private UsersService usersService;

	@Autowired
	PatientsService patientsService;
	
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	@RequestMapping(value = "/login")
	public String showLoginPage() {
		return LOGIN_PAGE;
	}

	@RequestMapping(value = "/logOut")
	public String showLogOutPage() {
		return LOG_OUT_PAGE;
	}

	@RequestMapping(value = "/accessDenied")
	public String showAccessDeniedPage() {
		return ACCESS_DENIED_PAGE;
	}

	@RequestMapping(value = "/newAccount")
	public String showNewAccountPage(Model model) {
			
		List<String> authorities = new ArrayList<>();
		authorities.add("ROLE_SECRETARY");
		authorities.add("ROLE_DOCTOR");
		authorities.add("ROLE_ADMIN");
		model.addAttribute("authorities", authorities);
		
		model.addAttribute("user", new User());
		return CREATE_NEW_ACCOUNT_PAGE;

	}

	@RequestMapping(value = "/createAccount", method = RequestMethod.POST)
	public String createAccount(@Valid User user, BindingResult result, Model model) {

		if (result.hasErrors()) {
			List<String> authorities = new ArrayList<>();
			authorities.add("ROLE_SECRETARY");
			authorities.add("ROLE_DOCTOR");
			authorities.add("ROLE_ADMIN");
			model.addAttribute("authorities", authorities);
			return CREATE_NEW_ACCOUNT_PAGE;
			
		} else {

			user.setEnabled(true);

			if (usersService.exists(user.getUsername())) {
				result.rejectValue("username", "DuplicateKey.user.username");
				return CREATE_NEW_ACCOUNT_PAGE;
			}

			usersService.saveUser(user);
			
			model.addAttribute("successMsg","Account Created");
			
			List<Patient> patientList = patientsService.getAllPatients();
			model.addAttribute("patients",patientList);
			
			return ConsultationsController.PATIENTS_PAGE;
		}

	}
	
	@RequestMapping(value = "/allUsers", method = RequestMethod.GET)
	public String showViewAllCustomesPage(Model model) {

		List<User> allUsers = usersService.getAllUsers();

		model.addAttribute("allUsers", allUsers);

		return USERS_PAGE;
	}
	
	@RequestMapping(value = "/updateUserInfo", method = RequestMethod.GET)
	public String updateUserInfo(@RequestParam(value = "id", required = true) String username,
			Model model) {

		User loggedInUser = usersService.getUser(username);
		
		List<String> authorities = new ArrayList<>();
		authorities.add("ROLE_SECRETARY");
		authorities.add("ROLE_DOCTOR");
		authorities.add("ROLE_ADMIN");
		model.addAttribute("authorities", authorities);
		
		model.addAttribute("user",loggedInUser);
		
		return UPDATE_USER_INFO_PAGE;
	}
	
	@RequestMapping(value = "updateUserInfo", method = RequestMethod.POST)
	public String processUpdateUserInfo(@Valid User user, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return UPDATE_USER_INFO_PAGE;
		} else {
			usersService.updateUser(user);
			List<User> allUsers = usersService.getAllUsers();
			model.addAttribute("allUsers", allUsers);
			return USERS_PAGE;
		}

	}
	
	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public String showDeleteCustomerPage(@RequestParam("id") String username, Model model, Principal principal) {

		if (username.equals(principal.getName())) {
			List<User> allUsers = usersService.getAllUsers();
			model.addAttribute("allUsers", allUsers);
			model.addAttribute("errorMsg","Cannot delete your own account");
			return USERS_PAGE;
		}
		
		usersService.deleteUser(username);

		List<User> allUsers = usersService.getAllUsers();
		model.addAttribute("allUsers", allUsers);
		return USERS_PAGE;
	}


}
