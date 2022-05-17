package com.zscalerlabsession.zscalerlabsession.response;

import com.zscalerlabsession.zscalerlabsession.Model.Customer;

public class GetCustomerResponse
{
    Customer customer;
    double balance;

    public GetCustomerResponse(Customer customer, double balance) {
        this.customer = customer;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

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
