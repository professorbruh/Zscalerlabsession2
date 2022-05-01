package com.zscalerlabsession.zscalerlabsession.serviceimpl;

import com.zscalerlabsession.zscalerlabsession.Model.Transaction;
import com.zscalerlabsession.zscalerlabsession.Repository.TransactionRepository;
import com.zscalerlabsession.zscalerlabsession.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Transaction createTransaction(Transaction transactionDetails) {
        return transactionRepository.save(transactionDetails);




    }

    @Override
    public Transaction getTransactionbyID(long id) {
        return transactionRepository.getById(id);
    }


}
