package com.bank.details.dao;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CustomerDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int custId;
	
	private String firstName;
	private String lastName;
	private int transactions;
	private int balance;

	
	@OneToMany(mappedBy = "custID", cascade=CascadeType.ALL)
	private List<AccountDetailsEntity> accountDetails;

	
	public int getCustId() {
		return custId;
	}


	public void setCustId(int custId) {
		this.custId = custId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public List<AccountDetailsEntity> getAccountDetails() {
		return accountDetails;
	}


	public void setAccountDetails(List<AccountDetailsEntity> accountDetails) {
		this.accountDetails = accountDetails;
	}


	public int getTransactions() {
		return transactions;
	}


	public void setTransactions(int transactions) {
		this.transactions = transactions;
	}


	public int getBalance() {
		return balance;
	}


	public void setBalance(int balance) {
		this.balance = balance;
	}


	public CustomerDetailsEntity() {

	}


	public CustomerDetailsEntity(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	

}
