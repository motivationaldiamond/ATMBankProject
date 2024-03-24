package com.motivationaldiamond.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransactionTest {

	private Transaction transaction;

	@BeforeEach
	public void setUp() {
		transaction = new Transaction();
		transaction.setId(1L);
		transaction.setTransactionType("Deposit");
		transaction.setAmount(1000.0);
		transaction.setTimestamp(LocalDateTime.now());
		transaction.setTransactionStatus("Completed");

		// Set up a dummy account
		Account account = new Account();
		account.setAccountNumber(123456789);
		transaction.setAccount(account);
	}

	@Test
	public void testGetters() {
		assertEquals(1L, transaction.getId());
		assertEquals("Deposit", transaction.getTransactionType());
		assertEquals(1000.0, transaction.getAmount());
		assertTrue(transaction.getTimestamp().isBefore(LocalDateTime.now().plusSeconds(1)));
		assertEquals("Completed", transaction.getTransactionStatus());
		assertEquals(123456789, transaction.getAccount().getAccountNumber());
	}

	@Test
	public void testNotEquals() {
		Transaction anotherTransaction = new Transaction();
		anotherTransaction.setId(2L); // Different ID
		assertFalse(transaction.equals(anotherTransaction));
	}

	@Test
	public void testToString() {
		String expectedToString = "Transaction []";
		assertEquals(expectedToString, transaction.toString());
	}
}
