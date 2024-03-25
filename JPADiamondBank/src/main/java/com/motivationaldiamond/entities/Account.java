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
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "account")
	private List<TransactionRecord> transactionRecords;

	public Account() {

	}

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<TransactionRecord> getTransactionRecords() {
		return transactionRecords;
	}

	public void setTransactionRecords(List<TransactionRecord> transactionRecords) {
		this.transactionRecords = transactionRecords;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountNumber);
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
		return accountNumber == other.accountNumber;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", pin=" + pin + ", balance=" + balance + ", accountType="
				+ accountType + ", transactionLimit=" + transactionLimit + ", accountStatus=" + accountStatus
				+ ", user=" + user + ", transactionRecords=" + transactionRecords + "]";
	}

}
