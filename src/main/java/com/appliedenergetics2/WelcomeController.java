package com.appliedenergetics2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
public class WelcomeController {
	
	@GetMapping
	public String greetings() {
		return "WELCOME TO APPLIED ENERGETICS 2 MODPACK WIKI";
	}
}
