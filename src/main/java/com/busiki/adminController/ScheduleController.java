package com.busiki.adminController;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.busiki.model.DniKursu;
import com.busiki.model.Przystanek;
import com.busiki.model.Rozklad;
import com.busiki.service.DniKursuService;
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

	@RequestMapping(value = "/dodajGodzine", method = RequestMethod.POST)
	public String dodajGodzine(HttpServletRequest req) {
		String[] godz = req.getParameterValues("godz[]");
		String[] dni = req.getParameterValues("dni[]");
		long trasa = Long.parseLong(req.getParameter("trasa"));
		long r_info = Long.parseLong(req.getParameter("r_info"));
		List<DniKursu> d = new ArrayList<DniKursu>();
		for (int i = 0; i < dni.length; i++) {
			d.add(dniKursuService.getByName(dni[i]));
		}
		Rozklad r = new Rozklad();
		List<Przystanek> p = trasaPrzystanekService
				.getAllPrzystankiTrasy(trasaPrzystanekService
						.getByIdTrasaInfo(trasa));

		for (DniKursu dniKursu : d) {
			for (int i = 0; i < godz.length; i++) {
				r.setDniKursu(dniKursu);
				r.setPrzystanek(p.get(i));
				r.setTrasaInfo(trasaPrzystanekService.getByIdTrasaInfo(trasa));
				r.setRozkladInfo(rozkladInfoService.getById(r_info));
				r.setGodzina(godz[i]);
				rozkladService.create(r);
			}

		}

		return "";
	}
	
}
