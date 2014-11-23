package com.busiki.adminController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/serviceConfigureDynamic")
public class ScheduleController {

	@RequestMapping("")
	public String create() {
		return "ok";
	}

}
