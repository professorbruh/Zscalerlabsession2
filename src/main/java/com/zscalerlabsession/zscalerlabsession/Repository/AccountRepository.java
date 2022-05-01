package com.zscalerlabsession.zscalerlabsession.Repository;

import com.zscalerlabsession.zscalerlabsession.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zscalerlabsession.zscalerlabsession.Model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("select count(*) from Account")
    long count();

    @Query("select a from Account a where a.id=?1")
    Account fetchAccountById(long id);


}
