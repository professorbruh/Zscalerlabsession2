package com.zscalerlabsession.zscalerlabsession.response;

import com.zscalerlabsession.zscalerlabsession.Model.Customer;

public class GetCustomerResponse
{
    Customer customer;

    public GetCustomerResponse(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
