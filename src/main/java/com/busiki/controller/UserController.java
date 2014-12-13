package com.busiki.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.busiki.model.User;
import com.busiki.service.UserService;

@Controller
public class UserController {
	
	protected static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	@RequestMapping("userProfile/changeData")
	public String verifyPassword(@RequestParam("first_name") String name, 
			@RequestParam("last_name") String surname, 
			@RequestParam("phone") String phone, 
			@RequestParam("email") String email, 
			@RequestParam("idnumber") String idnumber, 
			@RequestParam("location") String location, 
			@RequestParam("password") String password) {
		logger.debug("SZCZEGOLY PRZED ZMIANA: " + name + " " + surname + " " + phone + " " + email + " " + idnumber + " " + location);
		if (userService.getPasswordByEmail(email) == password) {
			//changeUserDetails(name, surname, phone, email, idnumber, location);
			logger.debug("SZCZEGOLY PRZED ZMIANA: " + name + " " + surname + " " + phone + " " + email + " " + idnumber + " " + location);
			return "redirect:/userProfile";
		}
		else 
			return "redirect:/userProfile"; //z bledem, zaraz sie dowiem jak zwrocic blad do View
	}
	
	/*private void changeUserDetails(String name, String surname, String phone, String email, String idnumber, String location) {
		logger.debug("SZCZEGOLY PRZED ZMIANA: " + name + " " + surname + " " + phone + " " + email + " " + idnumber + " " + location);
		
		User user = userService.getAccountByUsername(email);
			user.setFirstName(name);
			user.setLastName(surname);
			user.setPhoneNumber(phone);
			user.setIdCardNumber(idnumber);
			user.setAddress(location);
	}*/
	
}