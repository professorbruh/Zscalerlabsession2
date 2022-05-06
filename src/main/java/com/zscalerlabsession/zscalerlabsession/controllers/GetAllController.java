package com.zscalerlabsession.zscalerlabsession.controllers;

import com.zscalerlabsession.zscalerlabsession.Model.Account;
import com.zscalerlabsession.zscalerlabsession.Model.Customer;
import com.zscalerlabsession.zscalerlabsession.Model.Transaction;
import com.zscalerlabsession.zscalerlabsession.Repository.AccountRepository;
import com.zscalerlabsession.zscalerlabsession.Repository.CustomerRepository;
import com.zscalerlabsession.zscalerlabsession.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/allRecords")
@RestController
public class GetAllController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @GetMapping("/transactions")
    public ResponseEntity<Object> getAllTransactions()
    {
        Iterable<Transaction> all_transactions= transactionRepository.fetchTransactions();
        return new ResponseEntity<Object>(all_transactions, HttpStatus.OK);
    }

    @GetMapping("/accounts")
    public ResponseEntity<Object> getAllAccounts()
    {
        Iterable<Account> all_accounts = accountRepository.fetchAccounts();
        return new ResponseEntity<Object>(all_accounts, HttpStatus.OK);
    }

    @GetMapping("/customers")
    public ResponseEntity<Object> getAllCustomers()
    {
        Iterable<Customer> all_customers = customerRepository.fetchCustomers();
        return new ResponseEntity<Object>(all_customers,HttpStatus.OK);
    }

}
