package webLayer;

import java.security.Principal;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value="/")
public class HomeController {

	private static String HOME_PAGE="home";
	private static Logger logger = Logger.getLogger(HomeController.class);
	

	@RequestMapping(method=RequestMethod.GET)
	public String showHomePage(Model model, Principal principal){
	
		return HOME_PAGE;
	}
	
}
