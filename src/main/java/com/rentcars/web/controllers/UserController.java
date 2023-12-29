package com.rentcars.web.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.rentcars.buisness.service.ICarsService;
import com.rentcars.buisness.service.IUserService;
import com.rentcars.web.models.requests.UserForm;


@Controller
public class UserController {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ICarsService carsService;
	
	
	@GetMapping("/register")
	public String getRegistrationPage(@ModelAttribute("user") UserForm userForm) {
		return "register";
	}
	
	@PostMapping("/register")
	public String saveUser(@ModelAttribute("user") UserForm userForm, Model model) {
		userService.addUser(userForm);
		model.addAttribute("message", "Registered Successfuly!");
		return "register";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("client-page")
	//public String userPage (Model model, Principal principal) {
	public String userPage (Model model) {
		
		//UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		//model.addAttribute("user", userDetails);
	    model.addAttribute("cars", carsService.getAllCars());

		return "client";
	}
	


}
