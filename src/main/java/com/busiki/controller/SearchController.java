package com.busiki.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.busiki.model.Przystanek;
import com.busiki.service.TrasaPrzystanekService;

@RestController
@RequestMapping("/searchController")
public class SearchController {

	protected static Logger logger = Logger.getLogger(SearchController.class);

	@Autowired
	private TrasaPrzystanekService trasaPrzystanekService;

	@RequestMapping(value = "/validateInputValue", method = RequestMethod.GET)
	public boolean validateInputValue(HttpServletRequest req) {
		logger.debug("Parametr " + req.getParameter("przystanek"));
		for (Przystanek s : trasaPrzystanekService.getAllPrzystanek()) {
			if (s.getNazwa().equals(req.getParameter("przystanek"))) {
				return true;
			}
		}
		return false;
	}

}
