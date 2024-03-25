package com.motivationaldiamond.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motivationaldiamond.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	
}
