package com.zscalerlabsession.zscalerlabsession.Repository;

import com.zscalerlabsession.zscalerlabsession.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zscalerlabsession.zscalerlabsession.Model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("select a from Transaction a where a.id=?1")
    Transaction fetchTransactionbyID(long id);

    @Query("select c from Transaction c")
    Iterable<Transaction> fetchTransactions();

}

