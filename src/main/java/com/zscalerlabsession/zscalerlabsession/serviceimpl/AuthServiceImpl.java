package com.zscalerlabsession.zscalerlabsession.serviceimpl;

import com.zscalerlabsession.zscalerlabsession.Model.Customer;
import com.zscalerlabsession.zscalerlabsession.Repository.CustomerRepository;
import com.zscalerlabsession.zscalerlabsession.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public Customer createCustomer(Customer customerDetails) {
        // TODO Auto-generated method stub

        customerDetails.setPassword(encoder.encode(customerDetails.getPassword()));

        return customerRepository.save(customerDetails);
    }

    @Override
    public Customer fetchCustomerByEmail(String emailId) {
        // TODO Auto-generated method stub
        return customerRepository.fetchCustomerByEmail(emailId);
    }
}
