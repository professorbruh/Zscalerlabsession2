package com.zscalerlabsession.zscalerlabsession.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private long accountNumber;
	private double balance;
	private String type;

	public void setId(long id) {
		this.id = id;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	 
	@Override
	public String toString() {
		return "AccountDetail [account_number=" + accountNumber + ", Balance=" + balance + ", AccountType=" + type +  "]";
	}

	public Account() {
		super();
	}


	public Account(long id, long accountNumber, double balance, String type) {
		super();
		this.id=id;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.type = type;
		 
	}

	public long getId() {
        return id;
    }


}
