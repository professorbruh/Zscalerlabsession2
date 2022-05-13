package com.zscalerlabsession.zscalerlabsession.response;

public class TransferHistoryResponse {
    private Iterable<Object> debited;
    private Iterable<Object> credited;

    public Iterable<Object> getDebited() {
        return debited;
    }

    public void setDebited(Iterable<Object> debited) {
        this.debited = debited;
    }

    public Iterable<Object> getCredited() {
        return credited;
    }

    public void setCredited(Iterable<Object> credited) {
        this.credited = credited;
    }

    public TransferHistoryResponse(Iterable<Object> debited, Iterable<Object> credited) {
        this.debited = debited;
        this.credited = credited;
    }
}
