package com.motivationaldiamond.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motivationaldiamond.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Customer findByUsername(String username);
}