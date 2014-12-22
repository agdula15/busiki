package com.busiki.controller;

import java.security.Principal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.busiki.model.Bus;
import com.busiki.model.Kurs;
import com.busiki.model.Rezerwacja;
import com.busiki.model.User;
import com.busiki.service.BusService;
import com.busiki.service.KursService;
import com.busiki.service.RezerwacjaService;
import com.busiki.service.UserService;

@Controller
public class ReservationController {

	protected static Logger logger = Logger
			.getLogger(ReservationController.class);

	@Autowired
	private KursService kursService;

	@Autowired
	private BusService busService;

	@Autowired
	private RezerwacjaService rezerwacjaService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "rezerwacja")
	public String rezerwuj(
			@RequestParam(value = "kursIdStart") long kursIdStart,
			@RequestParam(value = "kursIdEnd") long kursIdEnd, Model m) {
		logger.debug("Kurs id " + kursIdStart + "," + kursIdEnd);
		Kurs k = (Kurs) kursService.getById(kursIdStart);
		Kurs k2 = (Kurs) kursService.getById(kursIdEnd);
		Bus b = busService.getById(k.getBus().getId());
		m.addAttribute("kursStart", k);
		m.addAttribute("kursEnd", k2);
		m.addAttribute("bus", b);

		m.addAttribute("zajete", kursService.getZajeteMiejsca(k, k2));
		for (Integer i : kursService.getZajeteMiejsca(k, k2)) {
			logger.debug("Miejsce zarezerwowane:  " + i);
		}
		return "rezerwacja";
	}

	@RequestMapping(value = "rezerwacja/zatwierdzenie", method = RequestMethod.POST)
	public String rezerwacjaZatwierdzenie(HttpServletRequest req,
			Principal principal) {
		logger.debug("Parametr k1 " + req.getParameter("kurs1") + " k2: "
				+ req.getParameter("kurs2") + " miejsca: "
				+ req.getParameter("dorezerwacji[]"));
		String[] doRezerwacji = req.getParameterValues("dorezerwacji[]");
		Kurs k1 = kursService
				.getById(Long.parseLong(req.getParameter("kurs1")));
		Kurs k2 = kursService
				.getById(Long.parseLong(req.getParameter("kurs2")));
		User u = userService.getAccountByUsername(principal.getName());
		rezerwacjaService.zatwierdzRezerwacje(k1, k2, doRezerwacji, u);
		Rezerwacja r = new Rezerwacja();

		Set<Integer> miejsca = new HashSet<Integer>();
		for (String s : doRezerwacji) {
			miejsca.add(Integer.parseInt(s));
		}
		logger.debug(" k1 i k2 + " + k1.getId() + k2.getId());
		r.setDataRezerwacji(new Date());
		r.setMiejscaZarezerwowane(miejsca);
		r.setKurs(k1);
		r.setKurs2(k2);
		r.setUser(u);
		r.setIloscMiejscZarezerwowanych(miejsca.size());
		// r.setKosztBiletu(10.2f);
		rezerwacjaService.create(r);
		return "userProfile";
	}
}
