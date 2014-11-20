package com.busiki.adminController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.busiki.model.Bus;
import com.busiki.model.News;
import com.busiki.model.Przystanek;
import com.busiki.model.RozkladInfo;
import com.busiki.model.TrasaInfo;
import com.busiki.model.Ulga;
import com.busiki.model.User;
import com.busiki.service.BusService;
import com.busiki.service.NewsService;
import com.busiki.service.RozkladInfoService;
import com.busiki.service.TrasaPrzystanekService;
import com.busiki.service.UlgaService;
import com.busiki.service.UserService;

@Controller
public class AdminController {

	protected static Logger logger = Logger.getLogger(AdminController.class);

	@Autowired
	private NewsService newsService;

	@Autowired
	private UserService userService;

	@Autowired
	private TrasaPrzystanekService trasaPrzystanekService;

	@Autowired
	private UlgaService ulgaService;

	@Autowired
	private BusService busService;

	@Autowired
	private RozkladInfoService rozkladInfoService;

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

	@RequestMapping(value = "users/update", method = RequestMethod.POST)
	public String updateUser(@RequestParam("id") String id,
			@RequestParam("email") String email,
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("phoneNumber") String phoneNumber,
			@RequestParam("idCardNumber") String idCardNumber) {
		User u = userService.getById(Long.parseLong(id));
		u.setEmail(email);
		u.setFirstName(firstName);
		u.setLastName(lastName);
		u.setIdCardNumber(idCardNumber);
		u.setPhoneNumber(phoneNumber);
		userService.updateUser(u);
		return "redirect:/users";
	}

	@RequestMapping("pracownicy")
	public String pracownicy(Model model) {
		model.addAttribute("pracownicy ", userService.getPracownicy());
		return "pracownicy";
	}

	@RequestMapping("news")
	public String news(Model model) {
		model.addAttribute("newsy", newsService.getAll());
		return "news";
	}

	// nazwa metody addStudent? :P
	@RequestMapping(value = "news/edit", method = RequestMethod.POST)
	public String addStudent(@ModelAttribute("news") News n) {
		News news = newsService.getNewsById(n.getId());
		news.setTresc(n.getTresc());
		news.setTresc(n.getTytul());
		newsService.update(news);
		return "redirect:/news";
	}

	@RequestMapping("trasa")
	public String trasa(Model model) {
		List<TrasaInfo> a = trasaPrzystanekService.getAllTrasy();
		for (TrasaInfo t : a) {
			Hibernate.initialize(t.getPrzystanek());
		}
		model.addAttribute("trasy", a);
		return "trasa";
	}

	@RequestMapping("trasaadd")
	public String trasaAdd(Model model) {
		model.addAttribute("przystanki",
				trasaPrzystanekService.getAllPrzystanek());
		return "addTrasa";
	}

	@RequestMapping(value = "trasaadd/add", method = RequestMethod.POST)
	public String trasaAddElement(@RequestParam("numer") String numer,
			@RequestParam("string") String string) {
		logger.debug("Dodawanie trasy: " + numer + " " + string);
		trasaPrzystanekService.dodajTrase(numer, string);
		return "redirect:/trasa";
	}

	@RequestMapping("przystanek")
	public String przystanek(Model model) {
		model.addAttribute("przystanki",
				trasaPrzystanekService.getAllPrzystanek());
		return "przystanek";
	}

	@RequestMapping(value = "przystanek/addorupdate", method = RequestMethod.POST)
	public String addorupdate(@ModelAttribute("przystanek") Przystanek p) {
		if (trasaPrzystanekService.getByIdPrzystanek(p.getId()) != null) {
			Przystanek p2 = trasaPrzystanekService.getByIdPrzystanek(p.getId());
			p2.setNazwa(p.getNazwa());
			p2.setNumer(p.getNumer());
			trasaPrzystanekService.updatePrzystanek(p2);

		} else {
			Przystanek p3 = new Przystanek();
			p3.setNazwa(p.getNazwa());
			p3.setNumer(p.getNumer());
			trasaPrzystanekService.createPrzystanek(p3);
		}

		return "redirect:/przystanek";
	}

	@RequestMapping("przystanek/delete/{id}")
	public String removePrzystanek(@PathVariable long id) {
		if (trasaPrzystanekService.isPrzystanekRemovable(id))
			trasaPrzystanekService.deletePrzystanek(id);

		return "redirect:/przystanek";
	}

	@RequestMapping("bus")
	public String bus(Model model) {
		model.addAttribute("bus", busService.getAll());
		return "bus";
	}

	@RequestMapping(value = "bus/addorupdate", method = RequestMethod.POST)
	public String addorupdatebus(@ModelAttribute("bus") Bus b) {
		if (busService.getById(b.getId()) != null) {
			Bus b2 = busService.getById(b.getId());
			b2.setNazwa(b.getNazwa());
			b2.setNr(b.getNr());
			b2.setMiejscaSiedzace(b.getMiejscaSiedzace());
			b2.setMiejscaStojace(b.getMiejscaStojace());
			busService.updateBus(b2);

		} else {
			Bus b3 = new Bus();
			b3.setNazwa(b.getNazwa());
			b3.setNr(b.getNr());
			b3.setMiejscaSiedzace(b.getMiejscaSiedzace());
			b3.setMiejscaStojace(b.getMiejscaStojace());
			busService.create(b3);
		}

		return "redirect:/bus";
	}

	@RequestMapping("bus/delete/{id}")
	public String removeBus(@PathVariable long id) {
		busService.delete(busService.getById(id));
		return "redirect:/bus";
	}

	@RequestMapping("promocje")
	public String promocje(Model model) {
		model.addAttribute("ulgi", ulgaService.getAll());
		return "promocje";
	}

	@RequestMapping("promocje/dodaj")
	public String addPromo(@RequestParam("opis") String opis,
			@RequestParam("wartosc") double wartosc) {
		ulgaService.create(opis, wartosc);

		return "redirect:/promocje";
	}

	@RequestMapping(value = "promocje/edytuj", method = RequestMethod.POST)
	public String editPromo(@RequestParam("id") long id,
			@RequestParam("opis") String opis,
			@RequestParam("wartosc") String wartosc) {
		Ulga ulga = ulgaService.getById(id);
		ulga.setOpis(opis);

		// Ta czesc logiki powinna pojsc do ulgaService????
		String string = wartosc.replace('%', ' ');
		logger.debug("STRING VALUE " + string);

		ulga.setWartoscUlgi(Double.parseDouble(string) / 100);

		ulgaService.edit(ulga);

		return "redirect:/promocje";
	}

	@RequestMapping(value = "promocje/usun/{id}")
	public String removePromo(@PathVariable long id) {
		ulgaService.delete(id);
		return "redirect:/promocje";
	}

	@RequestMapping(value = "schedule")
	public String schedule(Model m) {
		m.addAttribute("rozklady", rozkladInfoService.getAll());
		return "schedule";
	}

	@RequestMapping(value = "dodajRozklad", method = RequestMethod.POST)
	public String dodajRozklad(@RequestParam("dp1") String dataPoczatku,
			@RequestParam("dp2") String dataKonca) {
		RozkladInfo rozkladInfo = new RozkladInfo();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		Date d1;
		try {
			d1 = formatter.parse(dataPoczatku);
			rozkladInfo.setDataOd(d1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// DataOd i DataDo nie powinny byc typu Date?
		try {
			Date d2 = formatter.parse(dataKonca);
			rozkladInfo.setDataDo(d2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rozkladInfoService.create(rozkladInfo);
		return "redirect:/schedule";
	}

	@RequestMapping(value = "scheduleEdit",method = RequestMethod.GET)
	public String scheduleTrasy(@RequestParam(value="rid") long rid, Model m) {
		m.addAttribute("r_info", rozkladInfoService.getById(rid));
		m.addAttribute("trasy", trasaPrzystanekService.getAllTrasy());
		return "scheduleSelectTrasa";
	}

/*	@RequestMapping(value = "scheduleSelectTrasa{id}")
	public String scheduleSelectTrasa(@PathVariable long id, Model m) {
		logger.debug("Paramter id: " + id);
		
		return "scheduleSelectTrasa";
	}*/
	
	@RequestMapping(value = "scheduleConfigure",method = RequestMethod.GET)
	public String scheduleConfigure(@RequestParam(value="rid") long rid, @RequestParam(value="tid") long tid, Model m) {
		m.addAttribute("r_info", rozkladInfoService.getById(rid));
		m.addAttribute("trasy", trasaPrzystanekService.getByIdTrasaInfo(tid));
		return "scheduleConfigure";
	}
}
