package com.app.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@CrossOrigin(origins = "*")
public class HomeController {
	public HomeController() {
		System.out.println("In Home Controller...");
	}

	/*
	 * public String showHomePage() { return "/index"; }
	 */
	
	@RequestMapping("/test")
	public String greeting() {
		System.out.println("in greeting in Home Controller");
		return "Hello, REST !!!!";
	}
}
