package com.motivationaldiamond.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.motivationaldiamond.entities.Customer;
import com.motivationaldiamond.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<Customer> index() {
		return customerRepository.findAll();
	}

	@Override
	public Customer findById(long id) {
		Optional<Customer> customer = customerRepository.findById(id);
		return customer.orElse(null);
	}

	@Override
	public Customer create(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer update(long id, Customer customer) {
		Optional<Customer> existingCustomer = customerRepository.findById(id);
		if (existingCustomer.isPresent()) {
			customer.setId(id);
			return customerRepository.save(customer);
		} else {
			return null;
		}
	}

	@Override
	public boolean delete(long id) {
		Optional<Customer> existingCustomer = customerRepository.findById(id);
		if (existingCustomer.isPresent()) {
			customerRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean disableCustomer(long customerId) {
		Optional<Customer> customerOpt = customerRepository.findById(customerId);
		if (customerOpt.isPresent()) {
			Customer customer = customerOpt.get();
			customer.setActive(false);
			customerRepository.save(customer);
			return true;
		}
		return false;
	}

	@Override
	public boolean enableCustomer(long customerId) {
		Optional<Customer> customerOpt = customerRepository.findById(customerId);
		if (customerOpt.isPresent()) {
			Customer customer = customerOpt.get();
			customer.setActive(true);
			customerRepository.save(customer);
			return true;
		}
		return false;
	}

	@Override
	public Customer resetPassword(long customerId, String newPassword) {
		Optional<Customer> customerOpt = customerRepository.findById(customerId);
		if (customerOpt.isPresent()) {
			Customer customer = customerOpt.get();
			String encodedPassword = passwordEncoder.encode(newPassword);
			customer.setPassword(encodedPassword);
			return customerRepository.save(customer);
		}
		return null;
	}
}
