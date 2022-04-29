package com.zscalerlabsession.zscalerlabsession.response;

import java.util.Date;

public class CustomResponseForLogin {
    private Date timestamp;
    private String message;
    private String status;
    private String jwt;

    public CustomResponseForLogin(Date timestamp, String message, String status, String jwt, String name, String emailId) {
        this.timestamp = timestamp;
        this.message = message;
        this.status = status;
        this.jwt = jwt;
        this.name = name;
        this.emailId = emailId;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    private String name;
    private String emailId;



}
