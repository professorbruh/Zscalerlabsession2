package com.zscalerlabsession.zscalerlabsession.response;

import java.util.Date;

public class ResponseForFailedTransaction {
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public ResponseForFailedTransaction(Date timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private Date timestamp;
    private String message;
}
