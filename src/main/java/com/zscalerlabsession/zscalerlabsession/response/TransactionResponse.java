package com.zscalerlabsession.zscalerlabsession.response;

import java.sql.Date;

public class TransactionResponse {

    private long id;
    private long sender;
    private long receiver;
    private double amount;
    private String status;

    public TransactionResponse(long id, long sender, long receiver, double amount, String status, Date date) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.status = status;
        this.date = date;
    }
    public TransactionResponse(long sender, long receiver, double amount, String status) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.status = status;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSender() {
        return sender;
    }

    public void setSender(long sender) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private Date date;



}
