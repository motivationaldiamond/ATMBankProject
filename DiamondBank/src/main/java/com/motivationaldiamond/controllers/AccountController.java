package com.motivationaldiamond.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
}