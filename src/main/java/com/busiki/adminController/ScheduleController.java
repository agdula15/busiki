package com.busiki.adminController;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.busiki.model.DniKursu;
import com.busiki.model.Kurs;
import com.busiki.model.Przystanek;
import com.busiki.model.Rozklad;
import com.busiki.model.RozkladInfo;
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
	private String dat;
	private long trasa, r_info;
	private List<DniKursu> d = new ArrayList<DniKursu>();
	private Map<Integer, Przystanek> p;
	private List<Integer> dniDlaKursu = new ArrayList<Integer>();
	private Rozklad r;
	private int numerKursuDanegoDnia = 1;
	private Kurs kurs;
	private Date dataPoczatku, dataKonca;
	private DateFormat df = DateFormat.getDateInstance();
	private Calendar start = Calendar.getInstance();
	private Calendar end = Calendar.getInstance();
	private RozkladInfo rInfo;

	@RequestMapping(value = "/dodajGodzine", method = RequestMethod.POST)
	public boolean dodajGodzine(HttpServletRequest req) {
		godz = req.getParameterValues("godz[]");
		dni = req.getParameterValues("dni[]");
		trasa = Long.parseLong(req.getParameter("trasa"));
		r_info = Long.parseLong(req.getParameter("r_info"));
		d.clear();
		for (int i = 0; i < dni.length; i++) {
			d.add(dniKursuService.getByName(dni[i]));
		}
		r = new Rozklad();
		p = trasaPrzystanekService.getAllPrzystankiTrasyByTrasaIdMap(trasa);
		long pId = p.get(1).getId(); 
		for (DniKursu dniKursu : d) {
			numerKursuDanegoDnia = rozkladService.updateNumerKursu(godz[0],
					trasa, dniKursu, pId);
			for (int i = 0; i < godz.length; i++) {
				r.setDniKursu(dniKursu);
				r.setPrzystanek(p.get(i+1));
				r.setTrasaInfo(trasaPrzystanekService.getByIdTrasaInfo(trasa));
				r.setRozkladInfo(rozkladInfoService.getById(r_info));
				r.setGodzina(godz[i]);
				r.setNumer(numerKursuDanegoDnia);
				rozkladService.create(r);
			}
		}
		return true;
	}

	@RequestMapping(value = "/zatwierdz", method = RequestMethod.GET)
	public String zatwierdzenieRozkladu(@RequestParam("rid") String rid) {
		/*rInfo = rozkladInfoService.getById(Long.parseLong(rid));
		kurs = new Kurs();
		dataPoczatku = rozkladInfoService.getById(r_info).getDataOd();
		dataKonca = rozkladInfoService.getById(r_info).getDataDo();
		start.setTime(dataPoczatku);
		end.setTime(dataKonca);
		for (Rozklad r : rozkladService.getAllByRozkladInfoID(Long
				.parseLong(rid))) {
			if (r.getDniKursu().getId() == 1) {
				dniDlaKursu.clear();
				dniDlaKursu.add(2);
				dniDlaKursu.add(3);
				dniDlaKursu.add(4);
				dniDlaKursu.add(5);
				dniDlaKursu.add(6);
			}
			if (r.getDniKursu().getId() == 2) {
				dniDlaKursu.clear();
				dniDlaKursu.add(7);
			}
			if (r.getDniKursu().getId() == 3) {
				dniDlaKursu.clear();
				dniDlaKursu.add(1);
			}
			for (Date date = start.getTime(); !start.after(end); start.add(
					Calendar.DATE, 1), date = start.getTime()) {
				if (dniDlaKursu.contains(start.get(Calendar.DAY_OF_WEEK))) {
					dat = df.format(date);
					kurs.setDataKursu(dat);
					kurs.setRozklad(r);
					kursService.create(kurs);
				}
			}
			start.setTime(dataPoczatku);
			end.setTime(dataKonca);
		}
		rInfo.setZatwierdzony(true);
		rozkladInfoService.update(rInfo);*/
		return "redirect:/schedule";
	}
}
