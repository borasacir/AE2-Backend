package com.appliedenergetics2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AE2Login {
	
	@GetMapping
	public String loginScreen() {
		return "This is the login screen.";
	}
	
	
}
