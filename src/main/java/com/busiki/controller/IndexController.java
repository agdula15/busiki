package com.busiki.controller;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.busiki.model.Przystanek;
import com.busiki.service.NewsService;
import com.busiki.service.TrasaPrzystanekService;
import com.busiki.service.UlgaService;

@Controller
public class IndexController {

	protected static Logger logger = Logger.getLogger(IndexController.class);

	@Autowired
	private NewsService newsService;
		
	@Autowired
	private TrasaPrzystanekService trasaPrzystanekService;
	
	@Autowired
	private UlgaService ulgaService;
	
	@RequestMapping("index")
	public String index(Model model) {
		model.addAttribute("newsy", newsService.getAll());
		return "index";
	}
	
	@RequestMapping("contact")
	public String contact(){
		return "contact";
	}
	
	@RequestMapping("timetable")
	public String timetable(Model model){
		model.addAttribute("przystanki", trasaPrzystanekService.getAllPrzystanek());
		model.addAttribute("ulgi", ulgaService.getAll());
		return "timetable";
	}
	
	//autocomplete:
	@RequestMapping(value="autocomplete", method = RequestMethod.POST, produces = "application/json", headers = "Accept=*/*")
	public @ResponseBody List<String> dajPrzystanki(@RequestParam(value = "term") String p ){
		List<String> result = new ArrayList<String>();
		 logger.debug("Autocomplete:");
		// iterate a list and filter by tagName
		for (Przystanek s : trasaPrzystanekService.getAllPrzystanek()) {
			if (s.getNazwa().toLowerCase().contains(p.toLowerCase())) {
				result.add(s.getNazwa());
				 logger.debug("Autocomplete: " + s.getNazwa());
			}
		}
		for (String string : result) {
			logger.debug("Autocomplete re: " + string);
		}
		
		return result;
	}
	
}
