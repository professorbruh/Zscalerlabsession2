package com.zscalerlabsession.zscalerlabsession.service;

import com.zscalerlabsession.zscalerlabsession.Model.Transaction;

public interface TransactionService {

    Transaction createTransaction(Transaction transactionDetails);

    Transaction getTransactionbyID(long id);


}
