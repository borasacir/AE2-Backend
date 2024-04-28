package com.appliedenergetics2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appliedenergetics2.model.User;
import com.appliedenergetics2.repository.UserRepository;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/screen")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	@PostMapping("/signup")
	public String registerUser(@RequestBody User user) {
		if (userRepository.findByUsername(user.getUsername())!=null) {
			return "Error, Username already taken!";
		}
		
		userRepository.save(user);
		return "User registered!";
	}
	@PostMapping("/login")
	public String loginUser(@RequestBody User user) {
		User foundUser = userRepository.findByUsername(user.getUsername());
		if(foundUser != null && foundUser.getPassword().equals(user.getPassword())) {
			return "Login successful!";
		}
		return "Invalid username or password!";
	}
}
