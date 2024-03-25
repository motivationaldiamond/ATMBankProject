package com.motivationaldiamond.services;

import java.util.List;

import com.motivationaldiamond.entities.Account;
import com.motivationaldiamond.entities.TransactionRecord;

public interface AccountService {

	Account createAccount(Account account);

	List<TransactionRecord> getAccountTransactions(long accountNumber);
}
