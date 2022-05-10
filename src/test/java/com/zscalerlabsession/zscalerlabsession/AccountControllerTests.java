package com.zscalerlabsession.zscalerlabsession;

import com.zscalerlabsession.zscalerlabsession.Model.Account;
import com.zscalerlabsession.zscalerlabsession.Repository.AccountRepository;
import com.zscalerlabsession.zscalerlabsession.controllers.AccountController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class AccountControllerTests{

    @Autowired
    AccountController accountController;

    @Autowired
    AccountRepository accountRepository;

    @Test
    void testDbConncetion(){
        assertTrue(accountRepository.count() > 0);
    }

    @Test
    void createAccountTest(){
        long num = accountRepository.count();
        Account account = new Account(213, 2334432, 2354.10, "salary");
        accountController.createAccount(account);

        assertTrue(accountRepository.count() == num + 1);
    }

    @Test
    void getDetailsTest(){
        Account account = accountRepository.fetchAccountById(1234);
        assertNull(account);
    }
}
