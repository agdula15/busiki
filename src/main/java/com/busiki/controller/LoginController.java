package com.busiki.controller;

import java.security.Principal;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.busiki.model.User;
import com.busiki.service.UserService;

@Controller
public class LoginController {
	
	protected static Logger logger = Logger.getLogger(LoginController.class);

	@Resource(name = "userService")
	private UserService userService;

	@RequestMapping("login")
	public String index() {
		return "login";
	}

	@RequestMapping("loginok")
	public String loginok(ModelMap model, Principal principal) {
		User user = userService.getAccountByUsername(principal.getName());
		model.addAttribute("user", user);
		if (userService.findRoleByName(user, "ROLE_ADMIN")) return "redirect:/admin";
		//logger.debug("logincon: " + userService.findRoleByName(user, "ROLE_ADMIN"));
		return "redirect:/index";
	}

	@RequestMapping("logoutok")
	public String logoutok() {
		return "logoutok";
	}

	@RequestMapping("loginfailed")
	public String loginfailed() {
		return "loginfailed";
	}

}
