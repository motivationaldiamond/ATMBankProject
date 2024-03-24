package com.motivationaldiamond.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest {

	private Account account;

	@BeforeEach
	public void setUp() {
		account = new Account();
		account.setAccountNumber(123456789);
		account.setPin(1234);
		account.setBalance(1000.0);
		account.setAccountType("Savings");
		account.setTransactionLimit(500.0);
		account.setAccountStatus("Active");
		// Set up a dummy customer
		Customer customer = new Customer();
		customer.setId(1L);
		customer.setFirstName("John");
		account.setCustomer(customer);
		// Set up dummy transactions
		List<Transaction> transactions = new ArrayList<>();
		transactions.add(new Transaction());
		account.setTransactions(transactions);
	}

	@Test
	public void testGetters() {
		assertEquals(123456789, account.getAccountNumber());
		assertEquals(1234, account.getPin());
		assertEquals(1000.0, account.getBalance());
		assertEquals("Savings", account.getAccountType());
		assertEquals(500.0, account.getTransactionLimit());
		assertEquals("Active", account.getAccountStatus());
		assertEquals("John", account.getCustomer().getFirstName());
		assertEquals(1, account.getTransactions().size());
	}

	@Test
	public void testNotEquals() {
		Account anotherAccount = new Account();
		anotherAccount.setAccountNumber(987654321); // Different account number
		assertFalse(account.equals(anotherAccount));
	}

	@Test
	public void testToString() {
		String expectedToString = "Account [accountNumber=123456789, pin=1234, balance=1000.0, accountType=Savings, "
				+ "transactionLimit=500.0, accountStatus=Active, customer=Customer [id=1, firstName=John], "
				+ "transactions=[Transaction []]]";
		assertEquals(expectedToString, account.toString());
	}
}
