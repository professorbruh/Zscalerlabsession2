package com.zscalerlabsession.zscalerlabsession.Request;


public class TransactionRequest {
    private String emailId;
    private double amount;

    public TransactionRequest(String emailId, double amount, String type) {
        this.emailId = emailId;
        this.amount = amount;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;


    public TransactionRequest(String email, double amount) {
        this.emailId = email;
        this.amount = amount;
    }

    public TransactionRequest(){
        super();
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String email) {
        this.emailId = email;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
