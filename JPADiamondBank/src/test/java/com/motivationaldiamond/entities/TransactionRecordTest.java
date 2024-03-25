package com.motivationaldiamond.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransactionRecordTest {

	private TransactionRecord transactionRecord;

	@BeforeEach
	public void setUp() {
		transactionRecord = new TransactionRecord();
		transactionRecord.setId(1L);
		transactionRecord.setTransactionType("Deposit");
		transactionRecord.setAmount(1000.0);
		transactionRecord.setTimestamp(LocalDateTime.now());
		transactionRecord.setTransactionStatus("Completed");

		// Set up a dummy account
		Account account = new Account();
		account.setAccountNumber(123456789);
		transactionRecord.setAccount(account);
	}

	@Test
	public void testGetters() {
		assertEquals(1L, transactionRecord.getId());
		assertEquals("Deposit", transactionRecord.getTransactionType());
		assertEquals(1000.0, transactionRecord.getAmount());
		assertTrue(transactionRecord.getTimestamp().isBefore(LocalDateTime.now().plusSeconds(1)));
		assertEquals("Completed", transactionRecord.getTransactionStatus());
		assertEquals(123456789, transactionRecord.getAccount().getAccountNumber());
	}

	@Test
	public void testNotEquals() {
		TransactionRecord anotherTransaction = new TransactionRecord();
		anotherTransaction.setId(2L); // Different ID
		assertFalse(transactionRecord.equals(anotherTransaction));
	}

	@Test
	public void testToString() {
		String expectedToString = "Transaction []";
		assertEquals(expectedToString, transactionRecord.toString());
	}
}
