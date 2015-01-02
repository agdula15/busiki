package com.busiki.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.busiki.model.User;
import com.busiki.service.UserService;

@Controller
public class UserController {
	
	protected static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	/*@RequestMapping("userProfile")
	public String userProfile() {
		logger.debug("USERRR: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		return "userProfile";
	}*/

	@RequestMapping(value = "userProfile/changeData", method=RequestMethod.POST)
	public String verifyPassword(@RequestParam("first_name") String name, 
			@RequestParam("last_name") String surname, 
			@RequestParam("phone") String phone, 
			@RequestParam("email") String email, 
			@RequestParam("idnumber") String idnumber, 
			@RequestParam("location") String location, 
			@RequestParam("password") String password) {

		if (userService.getPasswordByEmail(email).equals(password)) {
			userService.changeUserDetails(name, surname, phone, email, idnumber, location);
			return "redirect:/userProfile";
		}
		else {
			//bindingResult.rejectValue("password","password.notvalid","B³êdne has³o");
			return "redirect:/userProfile"; //zwroc z informacja o blednym hasle
		}

	}
	
}