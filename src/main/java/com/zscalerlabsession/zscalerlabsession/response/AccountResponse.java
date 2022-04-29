package com.zscalerlabsession.zscalerlabsession.response;

import com.zscalerlabsession.zscalerlabsession.Model.Account;

import java.util.Date;

public class AccountResponse{

    private Date timeStamp;
    private String message;
    private String status;
    private Account account;

    public AccountResponse() {
        super();
    }

    public AccountResponse(Date timeStamp, String message, String status, Account account) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.status = status;
        this.account = account;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
