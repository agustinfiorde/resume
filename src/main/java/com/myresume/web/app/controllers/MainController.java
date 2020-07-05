package com.myresume.web.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myresume.web.app.services.UserService;

@Controller
@RequestMapping("/")
public class MainController {

	
	@GetMapping("/imagen")
	public String imagen() {
		return "imagen";
	}
	
	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/lilith")
	public String lilith() {

		userService.lilith();
		
		return "redirect:/login";
	}
	
	
}