package com.jrp.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

	@Autowired
	CoronavirusDataService CoronavirusDataService;
	
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("LocationStats", CoronavirusDataService.getNewCases());
		model.addAttribute("totalReportedCases", CoronavirusDataService.totalCases());
		return "home";
	}
}
 