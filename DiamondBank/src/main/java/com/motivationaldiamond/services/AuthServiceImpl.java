package com.motivationaldiamond.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.motivationaldiamond.entities.Customer;
import com.motivationaldiamond.repositories.CustomerRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public Customer register(Customer customer) {
		String encryptedPassword = passwordEncoder.encode(customer.getPassword());
		customer.setPassword(encryptedPassword);
		customer.setActive(true);
		customer.setRole("standard");
		customer = customerRepo.saveAndFlush(customer); // save to DB
		return customer; // return user
	}

	@Override
	public Customer getCustomerByUsername(String username) {
		return customerRepo.findByUsername(username);
	}
}