package com.motivationaldiamond.services;

import com.motivationaldiamond.entities.Customer;

public interface AuthService {
	public Customer register(Customer customer);
	public Customer getCustomerByUsername(String username);


}
