package com.busiki.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.busiki.model.Kurs;
import com.busiki.model.Przystanek;
import com.busiki.model.Rozklad;
import com.busiki.model.RozkladInfo;
import com.busiki.model.TrasaInfo;
import com.busiki.service.KursService;
import com.busiki.service.RozkladInfoService;
import com.busiki.service.RozkladService;
import com.busiki.service.TrasaPrzystanekService;

@RestController
@RequestMapping("/searchController")
public class SearchController {

	protected static Logger logger = Logger.getLogger(SearchController.class);

	@Autowired
	private TrasaPrzystanekService trasaPrzystanekService;

	@Autowired
	private RozkladService rozkladService;

	@Autowired
	private RozkladInfoService rozkladInfoService;

	@Autowired
	private KursService kursService;
	
	@RequestMapping(value = "/validateInputValue", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String validateInputValue(HttpServletRequest req) {
		logger.debug("Parametr " + req.getParameter("przystanek"));
		for (Przystanek s : trasaPrzystanekService.getAllPrzystanek()) {
			if (s.getNazwa().equals(req.getParameter("przystanek"))) {
				return "true";
			}
		}
		return "false";
	}
	
	@RequestMapping(value = "/validateInputValue2", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String validateInputValue2(HttpServletRequest req) {
		String temp = "";
		Set<String> result = new HashSet<String>();
		for (TrasaInfo tr : trasaPrzystanekService.getAllTrasy()) {
			temp = tr.getPoczatek() + "-" + tr.getKoniec();
			result.add(temp);
		}
		JSONObject jsonObject = new JSONObject();
		logger.debug(" trasa " + req.getParameter("trasa"));
		if (result.contains(req.getParameter("trasa"))) {
			return "true";
		}
		return "false";
	}

	@RequestMapping(value = "/searchRezerwacja", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String searchRezerwacja(HttpServletRequest req) {
		String start = req.getParameter("start");
		String end = req.getParameter("end");
		String dzien = req.getParameter("dzien");
		Set<TrasaInfo> trasy = trasaPrzystanekService
				.getTrasyByNazwyPrzystankow(start, end);
		List<Rozklad> rozklad = new ArrayList<Rozklad>();
		List<Kurs> kurs = new ArrayList<Kurs>();
		RozkladInfo rI = rozkladInfoService.getByDate(dzien);
		if(rI==null){ return "false1"; }
		for (TrasaInfo t : trasy) {
			rozklad.addAll(rozkladService.getRozkladByCriteriaSearch(dzien, rI,
					t));
			logger.debug("Trasa1 : " + t.getId());
		}
		for (Rozklad r : rozklad) {
			kurs.addAll(kursService.getKursByCriteriaSearch(dzien,r.getId()));
			logger.debug("Rozklad1  : " + r.getId());
		}
		JSONObject jsonObject1 = new JSONObject();
		jsonObject1.put("kurs", kurs);
		logger.debug("Kursy json : " + jsonObject1.toString());
		return jsonObject1.toString();
	}
}
