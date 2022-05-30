package com.zscalerlabsession.zscalerlabsession.response;

import java.util.Date;

public class CustomerResponseForInvalidLogin
{
    private String message;
    private Date timestamp;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CustomerResponseForInvalidLogin(String message, Date timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}
