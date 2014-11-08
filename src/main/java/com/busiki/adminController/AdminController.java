package com.busiki.adminController;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.busiki.service.NewsService;
import com.busiki.service.UserService;

@Controller
public class AdminController {

	protected static Logger logger = Logger.getLogger(AdminController.class);

	@Autowired
	private NewsService newsService;

	@Autowired
	private UserService userService;

	@RequestMapping("admin")
	public String index() {
		return "admin";
	}

	@RequestMapping("users")
	public String users(Model model) {
		model.addAttribute("users", userService.getAll());
		return "users";
	}

	@RequestMapping("users/remove/{id}")
	public String removeUser(@PathVariable long id) {
		if (userService.findRoleByName(userService.getById(id), "ROLE_ADMIN")) {
			// wyswietl monit informujacy o niemoznosci usuniecia admina
		} else {
			// wyswietl komunikat pytajacy o potwierdzenie
			
			userService.delete(id);
		}
		
		return "redirect:/users";
	}

	@RequestMapping("/users/available")
	@ResponseBody
	public String available(@RequestParam String email, String id) {
		logger.debug("id: " + id + " email" + email);
		Boolean available = userService.getById(Long.parseLong(id)).getEmail()
				.equals(email);
		if (available) {
			return available.toString();
		}
		Boolean available2 = userService.getAccountByUsername(email) == null;
		return available2.toString();
	}

	@RequestMapping("news")
	public String news(Model model) { 
		model.addAttribute("newsy", newsService.getAll()); 
		return "news";
	}

}
