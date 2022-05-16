package com.zscalerlabsession.zscalerlabsession.Request;

public class TransferRequest
{
    private String sender;

    private long receiver;

    private double amount;

    private String password;


    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public TransferRequest(String sender, long receiver, double amount, String password, boolean status) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.password = password;
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TransferRequest(String sender, long receiver, double amount, String password) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.password = password;
    }

    public TransferRequest(){
        super();
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public long getReceiver() {
        return receiver;
    }

    public void setReceiver(long receiver) {
        this.receiver = receiver;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
