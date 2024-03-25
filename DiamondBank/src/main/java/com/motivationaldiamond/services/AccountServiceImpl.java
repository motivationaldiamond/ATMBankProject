package com.motivationaldiamond.services;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.motivationaldiamond.entities.Account;
import com.motivationaldiamond.entities.TransactionRecord;
import com.motivationaldiamond.entities.User;
import com.motivationaldiamond.repositories.AccountRepository;
import com.motivationaldiamond.repositories.TransactionRecordRepository;
import com.motivationaldiamond.repositories.UserRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private TransactionRecordRepository transactionRecordRepository;

	@Override
	public Account createAccount(Account account) {
		// Retrieve the user associated with the account
		User user = userRepo.findById(account.getUser().getId()).orElse(null);

		// Check if the user exists
		if (user == null) {
			// Handle the case where the user is not found
			return null;
		}

		// Generate a new account number
		String accountNumberString = generateAccountNumber();
		long accountNumber = Long.parseLong(accountNumberString);

		// Set the account number and user for the account
		account.setAccountNumber(accountNumber);
		account.setUser(user);

		// Save the account entity to the database
		return accountRepository.save(account);
	}

	private String generateAccountNumber() {
		Random random = new Random();
		int randomNumber = random.nextInt(90000000) + 10000000; // Generates a random number between 10000000 and
																// 99999999
		return String.valueOf(randomNumber);
	}

	@Override
	public List<TransactionRecord> getAccountTransactions(long accountNumber) {
		// Retrieve transaction records associated with the specified account number
		return transactionRecordRepository.findByAccount_AccountNumber(accountNumber);
	}
}
