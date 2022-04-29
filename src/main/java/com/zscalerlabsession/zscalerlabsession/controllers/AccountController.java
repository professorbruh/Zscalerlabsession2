package com.zscalerlabsession.zscalerlabsession.controllers;

import com.zscalerlabsession.zscalerlabsession.Model.Account;
import com.zscalerlabsession.zscalerlabsession.Repository.AccountRepository;
import com.zscalerlabsession.zscalerlabsession.response.AccountResponse;
import com.zscalerlabsession.zscalerlabsession.response.AccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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
        String status = "";
        if(newNum > num) {
            status = "200";
        }
        else{
            status = "4XX";
        }

        return new ResponseEntity<Object>(new AccountResponse(new Date(), "Account created", status, savedAccount), HttpStatus.OK);
    }
}
