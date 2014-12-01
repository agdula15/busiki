package com.busiki.adminController;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.busiki.model.DniKursu;
import com.busiki.model.Kurs;
import com.busiki.model.Przystanek;
import com.busiki.model.Rozklad;
import com.busiki.service.DniKursuService;
import com.busiki.service.KursService;
import com.busiki.service.RozkladInfoService;
import com.busiki.service.RozkladService;
import com.busiki.service.TrasaPrzystanekService;

@RestController
@RequestMapping("/serviceConfigureDynamic")
public class ScheduleController {

	protected static Logger logger = Logger.getLogger(ScheduleController.class);

	@Autowired
	private RozkladService rozkladService;
	@Autowired
	private RozkladInfoService rozkladInfoService;
	@Autowired
	private TrasaPrzystanekService trasaPrzystanekService;
	@Autowired
	private DniKursuService dniKursuService;
	@Autowired
	private KursService kursService;

	private String godz[], dni[];
	private long trasa, r_info;
	private List<DniKursu> d = new ArrayList<DniKursu>();
	private List<Przystanek> p;
	private List<Integer> dniDlaKursu = new ArrayList<Integer>();
	private Rozklad r;
	private int numerKursuDanegoDnia = 1;
	private Kurs kurs;

	@RequestMapping(value = "/dodajGodzine", method = RequestMethod.POST)
	public boolean dodajGodzine(HttpServletRequest req) {
		// Rozklad:
		godz = req.getParameterValues("godz[]");
		dni = req.getParameterValues("dni[]");
		trasa = Long.parseLong(req.getParameter("trasa"));
		r_info = Long.parseLong(req.getParameter("r_info"));
		d.clear();

		// Kursy:
		Date dataPoczatku = rozkladInfoService.getById(r_info).getDataOd();
		DateFormat df = DateFormat.getDateInstance();
		Date dataKonca = rozkladInfoService.getById(r_info).getDataDo();
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		start.setTime(dataPoczatku);
		end.setTime(dataKonca);
		logger.debug("Daty dataPoczatku" + dataPoczatku + " dataKonca"
				+ dataKonca + " start" + start + " end" + end);

		// kursy end;
		
		
		for (int i = 0; i < dni.length; i++) {
			d.add(dniKursuService.getByName(dni[i]));
		}
		r = new Rozklad();
		p = trasaPrzystanekService.getAllPrzystankiTrasy(trasaPrzystanekService
				.getByIdTrasaInfo(trasa));
		for (DniKursu dniKursu : d) {
			long pId = trasaPrzystanekService
					.getAllPrzystankiTrasy(
							trasaPrzystanekService.getByIdTrasaInfo(trasa))
					.get(0).getId();
			numerKursuDanegoDnia = rozkladService.updateNumerKursu(godz[0],
					trasa, dniKursu, pId);
			for (int i = 0; i < godz.length; i++) {
				r.setDniKursu(dniKursu);
				r.setPrzystanek(p.get(i));
				r.setTrasaInfo(trasaPrzystanekService.getByIdTrasaInfo(trasa));
				r.setRozkladInfo(rozkladInfoService.getById(r_info));
				r.setGodzina(godz[i]);
				r.setNumer(numerKursuDanegoDnia);
				rozkladService.create(r);
				
				
				//kursy
				if (dniKursu.getId() == 1) {
					dniDlaKursu.clear();
					dniDlaKursu.add(2);
					dniDlaKursu.add(3);
					dniDlaKursu.add(4);
					dniDlaKursu.add(5);
					dniDlaKursu.add(6);
					logger.debug("dniDlaKursu 1 size " + dniDlaKursu.size());

				}
				if (dniKursu.getId() == 2) {
					dniDlaKursu.clear();
					dniDlaKursu.add(7);
					logger.debug("dniDlaKursu 2 size " + dniDlaKursu.size());
				}
				if (dniKursu.getId() == 3) {
					dniDlaKursu.clear();
					dniDlaKursu.add(1);
					logger.debug("dniDlaKursu 3 size " + dniDlaKursu.size());
				}
				logger.debug("Daty startGetTime " + start.getTime()
						+ " endTime " + end.getTime() + " start day "
						+ start.get(Calendar.DAY_OF_WEEK));
				for (Date date = start.getTime(); !start.after(end); start.add(
						Calendar.DATE, 1), date = start.getTime()) { 
					logger.debug("Wchodze z pêtli czasu");
					/*
					 * if
					 * (dniDlaKursu.contains(start.get(Calendar.DAY_OF_WEEK))) {
					 * logger.debug("dniDlaKursu 4 size " + dniDlaKursu.size());
					 * kurs = new Kurs(); String dat = df.format(date); dat +=
					 * (" " + r.getGodzina()); kurs.setDataKursu(dat);
					 * kurs.setRozklad(r); kursService.create(kurs);
					 * 
					 * logger.debug("Kurs: " + kurs.getDataKursu() +
					 * kurs.getId() + kurs.getRozklad().getId()); }
					 */
					logger.debug("Wychodze z pêtli czasu");
				}
				
				//kursy end
			}

		}
		logger.debug("Jestem w Schedule 6" + dataKonca);
		logger.debug("Jestem w Schedule 7" + dataPoczatku);
		return true;
	}

}
