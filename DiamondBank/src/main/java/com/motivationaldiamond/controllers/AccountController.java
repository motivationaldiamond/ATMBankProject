package com.motivationaldiamond.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.motivationaldiamond.entities.Account;
import com.motivationaldiamond.entities.TransactionRecord;
import com.motivationaldiamond.entities.User;
import com.motivationaldiamond.services.AccountService;
import com.motivationaldiamond.services.UserService;

@RestController
@RequestMapping("/api")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private UserService userService;

	@GetMapping("/{userId}/accounts")
	public ResponseEntity<List<Account>> getAllUserAccounts(@PathVariable("userId") int userId) {
		// Call the service method to retrieve all accounts for the specified user
		List<Account> userAccounts = accountService.getAllUserAccounts(userId);

		// Check if any accounts were found
		if (!userAccounts.isEmpty()) {
			// Return the list of user accounts with a 200 OK status
			return ResponseEntity.ok(userAccounts);
		} else {
			// If no accounts were found, return a 404 Not Found status
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/{userId}/accounts/create")
	public ResponseEntity<?> createAccount(@PathVariable("userId") int userId, @RequestBody Account account) {
		// Retrieve the user object associated with the provided userId
		User user = userService.findById(userId);

		// Check if the user exists
		if (user == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Return 404 if user is not found
		}

		// Set the user for the account
		account.setUser(user);

		// Call the service method to create an account for the specified user
		Account newAccount = accountService.createAccount(account);

		// Check if the account was successfully created
		if (newAccount != null) {
			// Return the newly created account with a 201 Created status
			return ResponseEntity.status(HttpStatus.CREATED).body(newAccount);
		} else {
			// If the account creation failed, return a 400 Bad Request status
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/accounts/{accountNumber}/transactions")
	public ResponseEntity<?> viewAccountTransactions(@PathVariable("accountNumber") long accountNumber) {
		// Call the service method to retrieve account transactions
		List<TransactionRecord> transactions = accountService.getAccountTransactions(accountNumber);

		if (transactions != null) {
			// Return the list of transactions with a 200 OK response
			return ResponseEntity.ok().body(transactions);
		} else {
			// If the transactions couldn't be retrieved, return a 404 Not Found response
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	// Delete Account
	@DeleteMapping("/{userId}/accounts/{accountNumber}")
	public ResponseEntity<?> deleteAccount(@PathVariable("userId") int userId,
			@PathVariable("accountNumber") long accountNumber) {
		// Call the service method to delete the account
		boolean deleted = accountService.deleteAccount(userId, accountNumber);

		// Check if the account was successfully deleted
		if (deleted) {
			// Return 204 No Content status indicating successful deletion
			return ResponseEntity.noContent().build();
		} else {
			// If deletion failed, return 404 Not Found status
			return ResponseEntity.notFound().build();
		}
	}

	// Disable Account
	@PutMapping("/{userId}/accounts/{accountNumber}/disable")
	public ResponseEntity<?> disableAccount(@PathVariable("userId") int userId,
			@PathVariable("accountNumber") long accountNumber) {
		// Call the service method to disable the account
		boolean disabled = accountService.disableAccount(userId, accountNumber);

		// Check if the account was successfully disabled
		if (disabled) {
			// Return 204 No Content status indicating successful disablement
			return ResponseEntity.noContent().build();
		} else {
			// If disablement failed, return 404 Not Found status
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{userId}/accounts/{accountNumber}/status")
	public ResponseEntity<?> updateAccountStatus(@PathVariable("userId") int userId,
			@PathVariable("accountNumber") long accountNumber, @RequestParam("status") String newStatus) {

		// Call the service method to update the account status
		boolean updated = accountService.updateAccountStatus(userId, accountNumber, newStatus);

		// Check if the account status was successfully updated
		if (updated) {
			// Return a success response with a 200 OK status
			return ResponseEntity.ok().build();
		} else {
			// If the account was not found or the status update failed, return a 404 Not
			// Found status
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{userId}/accounts/{accountNumber}/status")
	public ResponseEntity<String> getAccountStatus(@PathVariable("userId") int userId,
			@PathVariable("accountNumber") long accountNumber) {

		// Call the service method to retrieve the account status
		String accountStatus = accountService.getAccountStatus(userId, accountNumber);

		// Check if the account status was found
		if (accountStatus != null) {
			// Return the account status with a 200 OK status
			return ResponseEntity.ok(accountStatus);
		} else {
			// If the account status was not found, return a 404 Not Found status
			return ResponseEntity.notFound().build();
		}
	}

}