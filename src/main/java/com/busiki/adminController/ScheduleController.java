package com.busiki.adminController;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/serviceConfigureDynamic")
public class ScheduleController {

	protected static Logger logger = Logger.getLogger(ScheduleController.class);

	@RequestMapping(method = RequestMethod.POST)
	public String create(@RequestParam(value = "godz[]") Map<String,String>[] g) {

		
			logger.debug("Godzina: " + g[0].values());
		
		return "ok";
	}

}
