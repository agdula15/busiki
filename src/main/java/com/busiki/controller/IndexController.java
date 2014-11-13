package com.busiki.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.busiki.service.BusStopService;
import com.busiki.service.NewsService;
import com.busiki.service.TrasaPrzystanekService;
import com.busiki.service.UlgaService;

@Controller
public class IndexController {

	protected static Logger logger = Logger.getLogger(IndexController.class);

	@Autowired
	private NewsService newsService;
	
	@Autowired
	private BusStopService busStopService;
	
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
	
}
