package com.zscalerlabsession.zscalerlabsession.Model;

import javax.persistence.*;

@Entity
public class Customer {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private long accountNumber;
    private String ifscCode;
    private String phoneNumber;
    private String address;
    private String branchName;
    private String emailId;
    private String password;

    public Customer() {
        super();
    }

    public Customer(Long id, String name, long accountNumber, String ifscCode, String phoneNumber, String address, String branchName, String emailId, String password) {
        this.id = id;
        this.name = name;
        this.accountNumber = accountNumber;
        this.ifscCode = ifscCode;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.branchName = branchName;
        this.emailId = emailId;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIfscCode(){
        return ifscCode;
    }

	@Override
	public String toString() {
		return "Customers [id=" + id + ", name=" + name + ", accountNumber=" + accountNumber + ", ifscCode=" + ifscCode
				+ ", phoneNumber=" + phoneNumber + ", address=" + address + ", branchName=" + branchName + ", emailId="
				+ emailId + ", password=" + password + "]";
	}

}
