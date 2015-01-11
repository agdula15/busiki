package com.busiki.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.busiki.model.Kurs;
import com.busiki.model.Rezerwacja;
import com.busiki.model.Rezerwacja.Status;
import com.busiki.model.Rozklad;
import com.busiki.service.BusService;
import com.busiki.service.KursService;
import com.busiki.service.RezerwacjaService;
import com.busiki.service.RozkladInfoService;
import com.busiki.service.RozkladService;
import com.busiki.service.TrasaPrzystanekService;
import com.busiki.service.UserService;

@RestController
public class ReservationRestController {

	protected static Logger logger = Logger
			.getLogger(ReservationRestController.class);

	@Autowired
	private KursService kursService;

	@Autowired
	private BusService busService;

	@Autowired
	private RezerwacjaService rezerwacjaService;

	@Autowired
	private UserService userService;

	@Autowired
	private RozkladService rozkladService;

	@Autowired
	private RozkladInfoService rozkladInfoService;

	@Autowired
	private TrasaPrzystanekService trasaPrzystanekService;

	@RequestMapping(value = "reservation/edycja", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String getReservationForKurs(
			@RequestParam(value = "term1") String trasa,
			@RequestParam(value = "term2") String data,
			@RequestParam(value = "term3") String numerKursu) {
		String[] startEnd = trasa.split("-");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		JSONObject jsonObject1 = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		JSONObject item;
		logger.debug("termy : " + trasa + data + numerKursu);
		Set<Rezerwacja> resulRezerwacja = new HashSet<Rezerwacja>();
		List<Rozklad> r = rozkladService
				.getAllByRozkladInfoIdAndTrasyIdAndNumerKursu(data,
						rozkladInfoService.getByDate(data),
						trasaPrzystanekService.getTrasyByNazwyPrzystankow(
								startEnd[0], startEnd[1]), numerKursu);
		/*
		 * for (TrasaInfo tr :
		 * trasaPrzystanekService.getTrasyByNazwyPrzystankow(
		 * startEnd[0],startEnd[1])) { logger.debug("trasa test " + tr.getId() +
		 * tr.getPoczatek() + tr.getKoniec()); } for (Rozklad rozklad : r) {
		 * logger.debug(" Rozklad test :" + rozklad.getId()); }
		 */
		List<Kurs> kursy = new ArrayList<Kurs>();
		for (Rozklad rozklad : r) {
			kursy.addAll(kursService.getKursByCriteriaSearch(data,
					rozklad.getId()));
		}
		for (Kurs k : kursy) {
			for (Rezerwacja rez : rezerwacjaService.getReservationsByKursId(k
					.getId())) {
				resulRezerwacja.add(rez);

			}
		}

		jsonObject1.put("trasa", trasaPrzystanekService
				.getTrasyByNazwyPrzystankow(startEnd[0], startEnd[1]));
		jsonObject1.put("data", data);
		jsonObject1.put("numerKursu", numerKursu);
		String[] result = new String[Rezerwacja.Status.values().length];
		for (int i = 0; i < Rezerwacja.Status.values().length; i++) {
			result[i] = Rezerwacja.Status.values()[i].toString();
		}
		jsonObject1.put("statusy", result);
		jsonObject1.put("rozklad", r);
		for (Rezerwacja rezerwacja : resulRezerwacja) {
			item = new JSONObject();
			item.put("idRezerwacja", rezerwacja.getId());
			item.put("fullName", rezerwacja.getUser().getFullName());
			item.put("userId", rezerwacja.getUser().getId());
			item.put("numerDokumentu", rezerwacja.getUser().getIdCardNumber());
			item.put("dataRezerwacji",
					sdf.format(rezerwacja.getDataRezerwacji()));
			item.put("start", rezerwacja.getKurs().getRozklad().getPrzystanek()
					.getNazwa());
			item.put("koniec", rezerwacja.getKurs2().getRozklad()
					.getPrzystanek().getNazwa());
			item.put("iloscMiejsc", rezerwacja.getIloscMiejscZarezerwowanych());
			Hibernate.initialize(rezerwacja.getMiejscaZarezerwowane());
			item.put("jakieMiejsca", rezerwacja.getMiejscaZarezerwowane());
			item.put("status", rezerwacja.getStatus());
			jsonArray.put(item);
			logger.debug("Rezerwacja r5 " + rezerwacja.getId()
					+ rezerwacja.getUser().getFullName());
		}
		jsonObject1.put("rezerwacje", jsonArray);
		return jsonObject1.toString();
	}

	@RequestMapping(value = "reservation/edycjaStatusu", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String edycjaStatusu(
			@RequestParam(value = "term1") String idRezerwacja,
			@RequestParam(value = "term2") String status) {
		logger.debug("termy : " + idRezerwacja + status );
		Rezerwacja r = rezerwacjaService.getById(Long.parseLong(idRezerwacja));
		r.setStatus(Status.valueOf(status));
		rezerwacjaService.update(r);
		logger.debug("Status" + Status.valueOf(status).toString());
		return "";
	}
	

}
