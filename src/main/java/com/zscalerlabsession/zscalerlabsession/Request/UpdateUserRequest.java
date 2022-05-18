package com.zscalerlabsession.zscalerlabsession.Request;

public class UpdateUserRequest {

    private String name;

    private String phoneNumber;

    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    private String emailId;

    private String current_emailId;

    public UpdateUserRequest(String name, String phoneNumber, String address, String emailId, String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.emailId = emailId;
        this.password = password;
    }

    private String password;
}
