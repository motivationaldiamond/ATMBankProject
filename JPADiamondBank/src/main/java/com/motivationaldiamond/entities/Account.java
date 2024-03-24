package com.motivationaldiamond.entities;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
public class Account {

	@Id
	@Column(name = "account_number")
	private long accountNumber;
	
	private int pin;
	
	private double balance;
	
	@Column(name = "account_type")
	private String accountType;
	
	@Column(name = "transaction_limit")
	private double transactionLimit;
	
	@Column(name = "account_status")
	private String accountStatus;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@OneToMany(mappedBy = "account")
    private List<Transaction> transactions;

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getTransactionLimit() {
		return transactionLimit;
	}

	public void setTransactionLimit(double transactionLimit) {
		this.transactionLimit = transactionLimit;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public int hashCode() {
	    return Objects.hash(accountNumber, accountStatus, accountType, balance, customer, pin, transactionLimit,
	            transactions);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return accountNumber == other.accountNumber && Objects.equals(accountStatus, other.accountStatus)
				&& Objects.equals(accountType, other.accountType)
				&& Double.doubleToLongBits(balance) == Double.doubleToLongBits(other.balance)
				&& Objects.equals(customer, other.customer) && pin == other.pin
				&& Double.doubleToLongBits(transactionLimit) == Double.doubleToLongBits(other.transactionLimit)
				&& Objects.equals(transactions, other.transactions);
	}

	@Override
	public String toString() {
	    String customerInfo = (customer != null) ? ("Customer [id=" + customer.getId() + ", firstName=" + customer.getFirstName() + "]") : "null";
	    return "Account [accountNumber=" + accountNumber +
	            ", pin=" + pin +
	            ", balance=" + balance +
	            ", accountType=" + accountType +
	            ", transactionLimit=" + transactionLimit +
	            ", accountStatus=" + accountStatus +
	            ", customer=" + customerInfo +
	            ", transactions=" + transactions +
	            "]";
	}

	
	
}
