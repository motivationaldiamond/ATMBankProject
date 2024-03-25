package com.motivationaldiamond.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motivationaldiamond.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	Account findByUser_IdAndAccountNumber(int userId, long accountNumber);

	List<Account> findByUser_Id(int userId);
	
}
