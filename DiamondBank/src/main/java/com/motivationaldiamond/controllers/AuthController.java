package com.motivationaldiamond.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.motivationaldiamond.entities.Customer;
import com.motivationaldiamond.services.AuthService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
public class AuthController {
	@Autowired
	private AuthService authService;

	@PostMapping("register")
	public Customer register(@RequestBody Customer customer, HttpServletResponse res) {
		if (customer == null) {
			res.setStatus(400);
			return null;
		}

		customer = authService.register(customer);
		return customer;
	}

	@GetMapping("authenticate")
	public Customer authenticate(Principal principal, HttpServletResponse res) {
		if (principal == null) { // no Authorization header sent
			res.setStatus(401);
			res.setHeader("WWW-Authenticate", "Basic");
			return null;
		}
		return authService.getCustomerByUsername(principal.getName());
	}
}