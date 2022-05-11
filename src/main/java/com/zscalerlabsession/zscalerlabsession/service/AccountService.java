package com.zscalerlabsession.zscalerlabsession.service;

import com.zscalerlabsession.zscalerlabsession.Model.Account;

public interface AccountService {
    Account fetchAccountByEmail(String email);
}
