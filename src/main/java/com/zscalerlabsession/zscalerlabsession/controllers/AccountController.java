package com.zscalerlabsession.zscalerlabsession.controllers;

import com.zscalerlabsession.zscalerlabsession.Model.Account;
import com.zscalerlabsession.zscalerlabsession.Repository.AccountRepository;
import com.zscalerlabsession.zscalerlabsession.response.AccountResponse;
import com.zscalerlabsession.zscalerlabsession.response.AccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @PostMapping("/create")
    public ResponseEntity<Object> createCAccount(@RequestBody Account account){
        long num = accountRepository.count();
        Account savedAccount = accountRepository.save(account);
        long newNum = accountRepository.count();
        String status = "", message = "";
        if(newNum > num) {
            status = "200";
            message = "Account created!";
        }
        else{
            message = "Account could not be created";
            status = "404";
        }

        return new ResponseEntity<Object>(new AccountResponse(new Date(), message, status, savedAccount), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getDetails(@PathVariable("id") long id){
        Optional<Account> foundAccount = accountRepository.findById(id);
        if(foundAccount.isPresent()){
            return new ResponseEntity<Object>(new AccountResponse(new Date(), "Account found!", "200", foundAccount.get()), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<Object>(new AccountResponse(new Date(), "Account not found!", "404", foundAccount.get()), HttpStatus.OK);
        }
    }
}
