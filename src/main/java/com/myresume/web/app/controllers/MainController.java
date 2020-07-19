package com.myresume.web.app.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myresume.web.app.services.UserService;

@Controller
@RequestMapping("/")
public class MainController {

	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String login(HttpSession session, Authentication usuario, ModelMap modelo,
			@RequestParam(required = false) String error) {

		try {
			if (usuario.isAuthenticated()) {
				return "redirect:/project/list";
			} else if (error != null && !error.isEmpty()) {
				modelo.addAttribute("error", "La dirección de mail o la contraseña que ingresó son incorrectas.");
			} 
			return "login";
		} catch (Exception e) {
			return "login";
		}
	}

	@GetMapping("/lilith")
	public String lilith() {
		userService.lilith();
		return "redirect:/login";
	}

	@GetMapping("/adam")
	public String adam() {
		userService.adam();
		return "redirect:/login";
	}

}
