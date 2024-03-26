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
		
		// Generate a random PIN
	    int pin = generatePin();

	 // Set the account number, user, and PIN for the account
	    account.setAccountNumber(accountNumber);
	    account.setUser(user);
	    account.setPin(pin);

		// Save the account entity to the database
		return accountRepository.save(account);
	}

	private String generateAccountNumber() {
		Random random = new Random();
		int randomNumber = random.nextInt(90000000) + 10000000; // Generates a random number between 10000000 and
																// 99999999
		return String.valueOf(randomNumber);
	}
	
	private int generatePin() {
	    Random random = new Random();
	    return random.nextInt(9000) + 1000; // Generates a random 4-digit PIN
	}

	@Override
	public List<TransactionRecord> getAccountTransactions(long accountNumber) {
		// Retrieve transaction records associated with the specified account number
		return transactionRecordRepository.findByAccount_AccountNumber(accountNumber);
	}

	@Override
	public boolean deleteAccount(int userId, long accountNumber) {
		// Retrieve the account to delete
		Account account = accountRepository.findByUser_IdAndAccountNumber(userId, accountNumber);

		// Check if the account exists
		if (account != null) {
			// Delete the account from the database
			accountRepository.delete(account);
			return true; // Return true indicating successful deletion
		} else {
			return false; // Return false indicating account not found
		}
	}

	@Override
	public boolean disableAccount(int userId, long accountNumber) {
		// Retrieve the account to disable
		Account account = accountRepository.findByUser_IdAndAccountNumber(userId, accountNumber);

		// Check if the account exists
		if (account != null) {
			// Update the account status to "DISABLED"
			account.setAccountStatus("DISABLED");
			// Save the updated account to the database
			accountRepository.save(account);
			return true; // Return true indicating successful disablement
		} else {
			return false; // Return false indicating account not found
		}
	}

	@Override
	public boolean updateAccountStatus(int userId, long accountNumber, String newStatus) {
		// Retrieve the account to update
		Account account = accountRepository.findByUser_IdAndAccountNumber(userId, accountNumber);

		// Check if the account exists
		if (account != null) {
			// Update the account status
			account.setAccountStatus(newStatus);
			// Save the updated account to the database
			accountRepository.save(account);
			return true; // Return true indicating successful status update
		} else {
			return false; // Return false indicating account not found
		}
	}

	@Override
	public String getAccountStatus(int userId, long accountNumber) {
		// Retrieve the account based on the user ID and account number
		Account account = accountRepository.findByUser_IdAndAccountNumber(userId, accountNumber);

		// Check if the account exists
		if (account != null) {
			// Return the account's status
			return account.getAccountStatus();
		} else {
			// If the account does not exist, return null
			return null;
		}
	}

	@Override
	public List<Account> getAllUserAccounts(int userId) {
		// Retrieve all accounts associated with the provided user ID
		List<Account> userAccounts = accountRepository.findByUser_Id(userId);

		// Return the list of user accounts
		return userAccounts;
	}

	@Override
	public boolean updateAccountBalance(int userId, long accountNumber, Double newBalance) {
		// Retrieve the account to update
		Account account = accountRepository.findByUser_IdAndAccountNumber(userId, accountNumber);

		// Check if the account exists
		if (account != null) {
			// Update the account balance
			account.setBalance(newBalance);
			// Save the updated account to the database
			accountRepository.save(account);
			return true; // Return true indicating successful update
		} else {
			return false; // Return false indicating account not found
		}
	}

	@Override
	public boolean updateAccountPin(int userId, long accountNumber, Integer newPin) {
		// Retrieve the account to update
		Account account = accountRepository.findByUser_IdAndAccountNumber(userId, accountNumber);

		// Check if the account exists
		if (account != null) {
			// Update the account pin
			account.setPin(newPin);
			// Save the updated account to the database
			accountRepository.save(account);
			return true; // Return true indicating successful update
		} else {
			return false; // Return false indicating account not found
		}
	}

}
