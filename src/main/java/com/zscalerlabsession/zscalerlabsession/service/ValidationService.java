package com.zscalerlabsession.zscalerlabsession.service;

import com.zscalerlabsession.zscalerlabsession.Model.Account;
import com.zscalerlabsession.zscalerlabsession.Model.Customer;

public interface ValidationService {

    boolean emailValidation(Customer customer);


    boolean accountNumberValidation(Account account);
}
