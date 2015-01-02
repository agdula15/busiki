package com.busiki.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.busiki.model.User;
import com.busiki.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {

	protected static Logger logger = Logger.getLogger(RegisterController.class);

	@Autowired
	private UserService userService;

	@ModelAttribute("user")
	public User constructUser() {
		return new User();
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields(new String[] { "firstName", "lastName",
				"email", "phoneNumber", "idCardNumber", "password",
				"password_again", "checkbox1" });
	}

	@RequestMapping
	// ("/register")
	public String showRegister() {

		return "user-register";
	}

	@RequestMapping(/* value="/register", */method = RequestMethod.POST)
	public String doRegister(@Valid @ModelAttribute("user") User user,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "user-register";
		}
		userService.createNewCustomer(user, user.getPassword());
		logger.debug(user.toString() + " zosta³ dodany do bazy daych");
		return "redirect:/registration_ok.html";
	}

	@RequestMapping("/available")
	@ResponseBody
	public String available(@RequestParam String email) {
		Boolean available = userService.getAccountByUsername(email) == null;
		return available.toString();
	}
}
