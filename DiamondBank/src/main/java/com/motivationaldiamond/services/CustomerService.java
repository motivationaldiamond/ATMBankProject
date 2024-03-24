package com.motivationaldiamond.services;

import java.util.List;

import com.motivationaldiamond.entities.Customer;

public interface CustomerService {

	List<Customer> index();

	Customer findById(long id);

	Customer create(Customer newCustomer);

	Customer update(long id, Customer customer);

	boolean delete(long id);

	boolean disableCustomer(long customerId);

	boolean enableCustomer(long customerId);

	Customer resetPassword(long customerId, String newPassword);
}
