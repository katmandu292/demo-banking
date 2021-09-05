package ro.dcatalin.demo.controller;

import java.security.Principal;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ro.dcatalin.demo.data.AccountEntity;
import ro.dcatalin.demo.data.ClientEntity;
import ro.dcatalin.demo.service.ClientService;

@Controller
public class NbrOneController {

	private Logger logger = Logger.getLogger(getClass().getName());

	@Autowired
	private ClientService clientSvc;

	@GetMapping("/giveMeASession")
	public String initialPageRender(Model showClientModel) {
		Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
		String userAuthName = auth.getName();
		logger.info("currently granting a session to " + userAuthName);
		AccountEntity theNewAccount = new AccountEntity();
		showClientModel.addAttribute("userName", userAuthName);
		showClientModel.addAttribute("message","currently granting a session to " + userAuthName);
		if (userAuthName.equals("anonymousUser")) {
			showClientModel.addAttribute("new_account",theNewAccount);
			return "fancy-login";
		} else {
			ClientEntity theNewClient = clientSvc.getClient(userAuthName);
			theNewAccount.setClientId(theNewClient.getClientId());
			showClientModel.addAttribute("new_account",theNewAccount);
			return "self-page";			
		}
		
	}
	
	// add request mapping for /leaders

	@GetMapping("/leaders")
	public String showLeaders() {
		
		return "leaders";
	}
	
	// add request mapping for /systems
	
	@GetMapping("/systems")
	public String showSystems() {
		
		return "systems";
	}
}
