package com.zscalerlabsession.zscalerlabsession.serviceimpl;

import com.zscalerlabsession.zscalerlabsession.Model.Account;
import com.zscalerlabsession.zscalerlabsession.Model.Customer;
import com.zscalerlabsession.zscalerlabsession.Repository.AccountRepository;
import com.zscalerlabsession.zscalerlabsession.Repository.CustomerRepository;
import com.zscalerlabsession.zscalerlabsession.controllers.AccountController;
import com.zscalerlabsession.zscalerlabsession.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountController accountController;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public Customer createCustomer(Customer customerDetails) {
        // TODO Auto-generated method stub

        customerDetails.setPassword(encoder.encode(customerDetails.getPassword()));
        long num = accountRepository.count();
        long accountNumber = 100000000001L + num;
        Account newAccount = new Account(0, accountNumber, 0.00, "saving");
        ResponseEntity<Object> obj = accountController.createAccount(newAccount);
        if (accountRepository.count() != num + 1) {
            return null;
        }
        customerDetails.setAccountNumber(accountNumber);
        return customerRepository.save(customerDetails);
    }

    @Override
    public Customer fetchCustomerByEmail(String emailId) {
        // TODO Auto-generated method stub
        return customerRepository.fetchCustomerByEmail(emailId);
    }

    @Override //Done by Tejesh
    public void updatePassword(String emailId, String password) {

        Customer cust = customerRepository.fetchCustomerByEmail(emailId);
        cust.setPassword(encoder.encode(password));
        customerRepository.save(cust);

    }
}
