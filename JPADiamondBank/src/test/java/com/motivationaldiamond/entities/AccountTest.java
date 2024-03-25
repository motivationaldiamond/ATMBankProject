package com.motivationaldiamond.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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

        // Create a dummy customer
        User user = new User();
        user.setId((int) 1L);
        user.setUsername("testuser");
        account.setUser(user);

        // Set up dummy transaction records
        List<TransactionRecord> transactionRecords = new ArrayList<>();
        TransactionRecord transactionRecord1 = new TransactionRecord();
        transactionRecord1.setId(1L);
        transactionRecord1.setAmount(100.0);
        transactionRecords.add(transactionRecord1);
        account.setTransactionRecords(transactionRecords);
    }

    @Test
    public void testGetters() {
        assertEquals(123456789, account.getAccountNumber());
        assertEquals(1234, account.getPin());
        assertEquals(1000.0, account.getBalance());
        assertEquals("Savings", account.getAccountType());
        assertEquals(500.0, account.getTransactionLimit());
        assertEquals("Active", account.getAccountStatus());
        assertNotNull(account.getUser());
        assertEquals("testuser", account.getUser().getUsername());
        assertEquals(1, account.getTransactionRecords().size());
    }

    @Test
    public void testSetters() {
        account.setAccountNumber(987654321);
        assertEquals(987654321, account.getAccountNumber());

        account.setPin(4321);
        assertEquals(4321, account.getPin());

        account.setBalance(2000.0);
        assertEquals(2000.0, account.getBalance());

        account.setAccountType("Checking");
        assertEquals("Checking", account.getAccountType());

        account.setTransactionLimit(1000.0);
        assertEquals(1000.0, account.getTransactionLimit());

        account.setAccountStatus("Inactive");
        assertEquals("Inactive", account.getAccountStatus());

        // Test setting user and transaction records to null
        account.setUser(null);
        assertNull(account.getUser());

        account.setTransactionRecords(null);
        assertNull(account.getTransactionRecords());
    }
}
