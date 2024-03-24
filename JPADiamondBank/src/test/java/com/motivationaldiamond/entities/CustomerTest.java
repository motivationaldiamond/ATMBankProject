package com.motivationaldiamond.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerTest {

	private Customer customer;

	@BeforeEach
	public void setUp() {
		customer = new Customer();
		customer.setId(1L);
		customer.setFirstName("John");
		customer.setLastName("Doe");
		customer.setUsername("johndoe");
		customer.setPassword("password");
		customer.setEmail("john@example.com");
		customer.setPhoneNumber("1234567890");
		customer.setActive(true);
		customer.setCreateDate(LocalDateTime.now());
		customer.setLastUpdate(LocalDateTime.now());
		customer.setAddress("123 Main St");
		customer.setAddress2("Apt 101");
		customer.setCity("New York");
		customer.setState("NY");
		customer.setZipcode("10001");

		// Set up dummy accounts
		List<Account> accounts = new ArrayList<>();
		Account account = new Account();
		account.setAccountNumber(123456789);
		accounts.add(account);
		customer.setAccounts(accounts);
	}

	@Test
	public void testGetters() {
		assertEquals(1L, customer.getId());
		assertEquals("John", customer.getFirstName());
		assertEquals("Doe", customer.getLastName());
		assertEquals("johndoe", customer.getUsername());
		assertEquals("password", customer.getPassword());
		assertEquals("john@example.com", customer.getEmail());
		assertEquals("1234567890", customer.getPhoneNumber());
		assertTrue(customer.isActive());
		assertEquals("123 Main St", customer.getAddress());
		assertEquals("Apt 101", customer.getAddress2());
		assertEquals("New York", customer.getCity());
		assertEquals("NY", customer.getState());
		assertEquals("10001", customer.getZipcode());

		// Test accounts
		assertEquals(1, customer.getAccounts().size());
		assertEquals(123456789, customer.getAccounts().get(0).getAccountNumber());
	}

	@Test
	public void testNotEquals() {
		Customer anotherCustomer = new Customer();
		anotherCustomer.setId(2L); // Different ID
		assertFalse(customer.equals(anotherCustomer));
	}

	@Test
	public void testToString() {
		String expectedToString = "Customer [id=1, firstName=John, lastName=Doe, username=johndoe, password=password, "
				+ "email=john@example.com, phoneNumber=1234567890, active=true, createDate=" + customer.getCreateDate()
				+ ", lastUpdate=" + customer.getLastUpdate() + ", address=123 Main St, address2=Apt 101, "
				+ "city=New York, state=NY, zipcode=10001, accounts=" + customer.getAccounts() + "]";
		assertEquals(expectedToString, customer.toString());
	}
}
