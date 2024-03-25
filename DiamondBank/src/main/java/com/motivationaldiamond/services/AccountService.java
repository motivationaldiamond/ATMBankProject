package com.motivationaldiamond.services;

import java.util.List;

import com.motivationaldiamond.entities.Account;
import com.motivationaldiamond.entities.TransactionRecord;

public interface AccountService {

	Account createAccount(Account account);

	List<TransactionRecord> getAccountTransactions(long accountNumber);

	boolean deleteAccount(int userId, long accountNumber);

	boolean disableAccount(int userId, long accountNumber);

	boolean updateAccountStatus(int userId, long accountNumber, String newStatus);

	String getAccountStatus(int userId, long accountNumber);

	List<Account> getAllUserAccounts(int userId);
}
