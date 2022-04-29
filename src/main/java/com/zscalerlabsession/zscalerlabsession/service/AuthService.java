package com.zscalerlabsession.zscalerlabsession.service;

import com.zscalerlabsession.zscalerlabsession.Model.Customer;
import org.springframework.stereotype.Service;


public interface AuthService
{
    Customer createCustomer(Customer customerDetails);

    Customer fetchCustomerByEmail(String emailId);
}
