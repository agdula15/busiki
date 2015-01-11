package com.busiki.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.busiki.model.Przystanek;
import com.busiki.model.Rezerwacja;
import com.busiki.service.KursService;
import com.busiki.service.NewsService;
import com.busiki.service.RezerwacjaService;
import com.busiki.service.RozkladService;
import com.busiki.service.TrasaPrzystanekService;
import com.busiki.service.UlgaService;
import com.busiki.service.UserService;

@Controller
public class IndexController {

	protected static Logger logger = Logger.getLogger(IndexController.class);

	@Autowired
	private NewsService newsService;

	@Autowired
	private TrasaPrzystanekService trasaPrzystanekService;

	@Autowired
	private UlgaService ulgaService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RezerwacjaService rezerwacjaService;
	
	@Autowired
	private KursService kursService;
	
	@Autowired
	private RozkladService rozkladService;

	@RequestMapping("index")
	public String index(Model model) {
		model.addAttribute("newsy", newsService.getAll());
		return "index";
	}

	@RequestMapping("contact")
	public String contact() {
		return "contact";
	}

	@RequestMapping("timetable")
	public String timetable(Model model) {
		model.addAttribute("przystanki",
				trasaPrzystanekService.getAllPrzystanek());
		model.addAttribute("ulgi", ulgaService.getAll());
		return "timetable";
	}
	
	@RequestMapping("userProfile")
	public String userProfile(Model model, Principal principal) {
		model.addAttribute("user", userService.getAccountByUsername(principal.getName())); //principal.getName() zwraca email zalogowanego u¿ytkownika
		List<Rezerwacja> reses = rezerwacjaService.getReservationsByUserId( userService.getAccountByUsername(principal.getName()).getId() );
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		
		//tabela reservations zawiera informacje potrzebne do wyswietlenia danych o rezerwacjach danego uzytkownika, kolejno:
		//[][0] = Data Odjazdu, [][1] = Przystanek odjazdu, [][2] = Data przyjazdu, [][3] = Przystanek przyjazdu, [][4] = miejsce w busie, [][5] = true/false z informacja o stanie platnosci
		Object[][] reservations = new Object[reses.size()][6]; 
		for (int i=0; i<reses.size(); i++){
			reservations[i][0] = df.format(reses.get(i).getKurs().getDataKursu());
			reservations[i][1] = kursService.getById(reses.get(i).getKurs().getId()).getRozklad().getPrzystanek().getNazwa();
			reservations[i][2] = df.format(reses.get(i).getKurs2().getDataKursu());
			reservations[i][3] = kursService.getById(reses.get(i).getKurs2().getId()).getRozklad().getPrzystanek().getNazwa();
			
			/*List<Integer> miejscaNOs = rezerwacjaService.getSeatNumbersByReservationId(reses.get(i).getId());
			logger.debug("JADZIEM: ");
			for (int j=0; j<miejscaNOs.size(); j++) { 
				logger.debug(miejscaNOs.get(j).toString());
			}*/
			//reservations[i][4] = "";
			/*for (Integer j : miejscaNOs) {
				reservations[i][4] = reservations[i][4] + (j + " ");
			}*/ 
			
			reservations[i][5] = reses.get(i).getCzyZaplacone() ? "true" : "false"; //brzydki hack ale z jakiegos powodu w serwlecie w userProfile.jsp nie moglem dzia³aæ na booleanach
		}
		
		model.addAttribute("reservationsInfo", reservations);
		
		/*logger.debug("JADZIEM: ");
		for (int i=0; i<reservations.length; i++) {
			for (int j=0; j<reservations[i].length; j++) {
				logger.debug(reservations[i][j]);
			}
		}*/
		
		return "userProfile";
	}
	
	@RequestMapping("reservationOk")
	public String reservationOk() {
		return "reservationOk";
	}
	
	

	// autocomplete:
	@RequestMapping(value = "autocomplete", method = RequestMethod.POST, produces = "application/json", headers = "Accept=*/*")
	public @ResponseBody List<String> dajPrzystankiZ(
			@RequestParam(value = "term") String p) {
		List<String> result = new ArrayList<String>();
		for (Przystanek s : trasaPrzystanekService.getAllPrzystanek()) {
			if (s.getNazwa().toLowerCase().contains(p.toLowerCase())) {
				result.add(s.getNazwa());
			}
		}
		return result;
	}

	@RequestMapping(value = "autocomplete2", method = RequestMethod.POST, produces = "application/json", headers = "Accept=*/*")
	public @ResponseBody Set<String> dajPrzystankiDo(
			@RequestParam(value = "term1") String p1,
			@RequestParam(value = "term2") String p2) {
		return trasaPrzystanekService.dajPrzystankiDo(p1, p2);
	}

	@RequestMapping(value = "search", method = RequestMethod.POST)
	public String search(HttpServletRequest req, Model model) {
		logger.debug("Dane: " + req.getParameter("searchFrom")
				+ req.getParameter("searchTo") + req.getParameter("data")
				+ req.getParameter("godzina"));
		return "timetable";
	}

}
