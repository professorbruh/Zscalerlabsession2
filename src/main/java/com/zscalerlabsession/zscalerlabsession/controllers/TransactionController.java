package com.zscalerlabsession.zscalerlabsession.controllers;

import com.zscalerlabsession.zscalerlabsession.Model.Account;
import com.zscalerlabsession.zscalerlabsession.Model.Customer;
import com.zscalerlabsession.zscalerlabsession.Model.Transaction;
import com.zscalerlabsession.zscalerlabsession.Repository.AccountRepository;
import com.zscalerlabsession.zscalerlabsession.Repository.TransactionRepository;
import com.zscalerlabsession.zscalerlabsession.Request.TransactionRequest;
import com.zscalerlabsession.zscalerlabsession.Request.TransferRequest;
import com.zscalerlabsession.zscalerlabsession.response.ResponseForFailedTransaction;
import com.zscalerlabsession.zscalerlabsession.response.TransactionResponse;
import com.zscalerlabsession.zscalerlabsession.service.AccountService;
import com.zscalerlabsession.zscalerlabsession.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RequestMapping("/transaction")
@RestController
@CrossOrigin
public class TransactionController {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AuthService authService;

    @Autowired
    PasswordEncoder encoder;





//
//    @GetMapping("/get")
//    public ResponseEntity<Object> get(@RequestBody Transaction transaction)
//    {
//        Transaction fetchtransaction = transactionRepository.getById(transaction.getId());
//        TransactionResponse response = new TransactionResponse(fetchtransaction.getId(),fetchtransaction.getSender(), fetchtransaction.getReceiver(),
//                fetchtransaction.getAmount(), );
//    }

    @PostMapping("/transfer")
    public ResponseEntity<Object> get(@RequestBody TransferRequest transaction)
    {

        if(transaction.getAmount()<=0)
        {
            ResponseForFailedTransaction response = new ResponseForFailedTransaction(new java.util.Date(),"Invalid Amount");
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }


        Customer customer = authService.fetchCustomerByEmail(transaction.getSender());

        if(!encoder.matches(transaction.getPassword(),customer.getPassword()))
        {
            ResponseForFailedTransaction response = new ResponseForFailedTransaction(new java.util.Date(),"Password Incorrect");
            return new ResponseEntity<Object>(response,HttpStatus.OK);
        }

        Account sender =accountService.fetchAccountByEmail(transaction.getSender());

        Account receiver = accountRepository.fetchAccountByAccountNumber(transaction.getReceiver());

        if(sender!=null && receiver!=null)
        {
            double prev_balance_sender = sender.getBalance();
            double prev_balance_receiver = receiver.getBalance();
            if(sender.getBalance()>=transaction.getAmount()) {
                long millis = System.currentTimeMillis();
                java.sql.Date date = new java.sql.Date(millis);
                sender.setBalance(sender.getBalance() - transaction.getAmount());
                receiver.setBalance(receiver.getBalance() + transaction.getAmount());
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

    @PostMapping("/direct")
    public ResponseEntity<Object> directTransfer(@RequestBody TransactionRequest transact){

        if(transact.getAmount()<=0)
        {
            ResponseForFailedTransaction response = new ResponseForFailedTransaction(new java.util.Date(),"Invalid Amount");
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }

        Account account = accountService.fetchAccountByEmail(transact.getEmailId());
        if(account.getBalance() + transact.getAmount() > 0){
            account.setBalance(account.getBalance() + transact.getAmount());
            accountRepository.save(account);
            ResponseForFailedTransaction response = new ResponseForFailedTransaction(new java.util.Date(),"Transaction successful");
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
        else{
            ResponseForFailedTransaction response = new ResponseForFailedTransaction(new java.util.Date(),"Insufficient Balance");
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }

    @PostMapping("/history")
    public ResponseEntity<Object> history(@RequestBody Customer customer)
    {
        Account account = accountService.fetchAccountByEmail(customer.getEmailId());

        Iterable<Transaction> all_transaction = transactionRepository.getTransactionbyAccountNumber(account.getAccountNumber(),account.getAccountNumber());

        Iterator<Transaction> iterator= all_transaction.iterator();
        int ctr = 0;
        while(iterator.hasNext())
        {   ctr++;
            Transaction transaction = iterator.next();

            if (transaction.getReceiver()!=account.getAccountNumber())
            {
                transaction.setAmount(transaction.getAmount()*-1);

            }
        }

        HashMap<String,Object> data= new HashMap();
        data.put("recordsTotal",ctr);
        data.put("recordsFiltered",ctr);
        data.put("draw",1);
        data.put("data",all_transaction);

        return new ResponseEntity<Object>(data,HttpStatus.OK);

    }
    @PostMapping("/reversal")
    public ResponseEntity<Object> reversal(@RequestBody TransferRequest transaction )
    {

        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        Account receiver =accountService.fetchAccountByEmail(transaction.getSender());
        Account sender = accountRepository.fetchAccountByAccountNumber(transaction.getReceiver());
        double prev_balance_receiver = receiver.getBalance()+ transaction.getAmount();
        double prev_balance_sender = sender.getBalance() - transaction.getAmount();

        sender.setBalance(prev_balance_sender);
        receiver.setBalance(prev_balance_receiver);
        accountRepository.save(sender);
        accountRepository.save(receiver);
        Transaction newTransaction = new Transaction(sender.getAccountNumber(), receiver.getAccountNumber(), transaction.getAmount(), "Failed", date);
        transactionRepository.save(newTransaction);
        TransactionResponse response = new TransactionResponse(newTransaction.getId(), newTransaction.getSender(), newTransaction.getReceiver(),
                newTransaction.getAmount(), newTransaction.getStatus(), date);
        return new ResponseEntity<Object>(response, HttpStatus.OK);


    }

}




