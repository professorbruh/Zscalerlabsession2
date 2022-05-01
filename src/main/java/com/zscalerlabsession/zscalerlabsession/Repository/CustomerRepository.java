package com.zscalerlabsession.zscalerlabsession.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zscalerlabsession.zscalerlabsession.Model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT a FROM Customer a WHERE a.emailId=?1")
    Customer fetchCustomerByEmail(String emailId);



}
