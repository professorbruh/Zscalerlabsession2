package com.zscalerlabsession.zscalerlabsession.response;

import java.util.Date;

public class CustomResponseForNoUser {
    private Date timestamp;

    public CustomResponseForNoUser(Date timestamp, String message, String status) {
        this.timestamp = timestamp;
        this.message = message;
        this.status = status;
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

    private String message;
    private String status;
}
