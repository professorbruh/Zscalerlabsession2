package com.zscalerlabsession.zscalerlabsession.controllers;

import com.zscalerlabsession.zscalerlabsession.Model.Account;
import com.zscalerlabsession.zscalerlabsession.Model.Transaction;
import com.zscalerlabsession.zscalerlabsession.Repository.AccountRepository;
import com.zscalerlabsession.zscalerlabsession.Repository.TransactionRepository;
import com.zscalerlabsession.zscalerlabsession.response.ResponseForFailedTransaction;
import com.zscalerlabsession.zscalerlabsession.response.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/transaction")
@RestController
public class TransactionController {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;

//
//    @GetMapping("/get")
//    public ResponseEntity<Object> get(@RequestBody Transaction transaction)
//    {
//        Transaction fetchtransaction = transactionRepository.getById(transaction.getId());
//        TransactionResponse response = new TransactionResponse(fetchtransaction.getId(),fetchtransaction.getSender(), fetchtransaction.getReceiver(),
//                fetchtransaction.getAmount(), );
//    }

    @PostMapping("/transfer")
    public ResponseEntity<Object> get(@RequestBody Transaction transaction)
    {
        Account sender = accountRepository.fetchAccountByAccountNumber(transaction.getSender());

        Account receiver = accountRepository.fetchAccountByAccountNumber(transaction.getReceiver());

        if(sender!=null && receiver!=null)
        {
            if(receiver.getBalance()>=transaction.getAmount()) {
                long millis = System.currentTimeMillis();
                java.sql.Date date = new java.sql.Date(millis);
                sender.setBalance(sender.getBalance() + transaction.getAmount());
                receiver.setBalance(receiver.getBalance() - transaction.getAmount());
                accountRepository.save(sender);
                accountRepository.save(receiver);
                Transaction newTransaction = new Transaction(sender.getAccountNumber(), receiver.getAccountNumber(), transaction.getAmount(), "Complete", date);
                transactionRepository.save(newTransaction);
                TransactionResponse response = new TransactionResponse(newTransaction.getId(), newTransaction.getSender(), newTransaction.getReceiver(),
                        newTransaction.getAmount(), newTransaction.getStatus(), date);
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            }
            else
            {
                ResponseForFailedTransaction response = new ResponseForFailedTransaction(new java.util.Date(),"Insufficient Balance");
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            }

        }
        else
        {
            ResponseForFailedTransaction response = new ResponseForFailedTransaction(new java.util.Date(),"Invalid Account Numbers");
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }

//        System.out.println(sender.toString());
//        Calendar calendar = Calendar.getInstance();
//        long timeMilli2 = calendar.getTimeInMillis();
//        TransactionResponse response = new TransactionResponse(sender.getId(), receiver.getId(), transaction.getAmount(),transaction.getStatus());
//        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

}
