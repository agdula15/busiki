package com.busiki.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.busiki.model.Przystanek;
import com.busiki.model.PrzystankiTrasy;
import com.busiki.model.TrasaInfo;
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

	@RequestMapping(value = "/searchRezerwacja", method = RequestMethod.POST)
	public boolean searchRezerwacja(HttpServletRequest req) {
		logger.debug("Parametr " + req.getParameter("start")
				+ req.getParameter("end") + req.getParameter("godz")
				+ req.getParameter("dzien"));
		Set<TrasaInfo> trasy = new HashSet<TrasaInfo>();
		String start=req.getParameter("start");
		String end=req.getParameter("end");
		boolean temp = false;
		for (PrzystankiTrasy pt : trasaPrzystanekService.getAllPrzystankiTrasy()) {
			if(pt.getPrzystanek().getNazwa().equals(start)){
				trasy.add(pt.getTrasaInfo());
			}
		}
		JSONObject jsonObject1 = new JSONObject();
		jsonObject1.put("dzien", req.getParameter("dzien"));
		jsonObject1.put("godz", req.getParameter("godz"));
		jsonObject1.put("start", req.getParameter("start"));
		jsonObject1.put("end", req.getParameter("end"));
		jsonObject1.put("result", trasy);
		logger.debug("test json" + jsonObject1.toString());
		
		 // { data: 2014-12-22, godz: 14:00, start: Lublin, end: Garwolin,
		 //result: [ { godz: 15:30, numertrasy: 1 }, { godz: 16:30, numertrasy:
		 // 1 }, { godz: 14:30, numertrasy: 2 } ] }
		 
		return true;
	}
}
