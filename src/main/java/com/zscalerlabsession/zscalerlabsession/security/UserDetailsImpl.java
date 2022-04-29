package com.zscalerlabsession.zscalerlabsession.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.zscalerlabsession.zscalerlabsession.Model.Customer;

import java.util.Collection;
//
//public class UserDetailsImpl implements UserDetails
//{
//    private static final long serialVersionUID = 1L;
//
//    static final Logger log = LoggerFactory.getLogger(UserDetailsImpl.class);
//    private long id;
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    private String name;
//    private String password;
//    private long accountnumber;
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return null;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public long getAccountnumber() {
//        return accountnumber;
//    }
//
//    public void setAccountnumber(long accountnumber) {
//        this.accountnumber = accountnumber;
//    }
//
//    public String getIfscCode() {
//        return ifscCode;
//    }
//
//    public void setIfscCode(String ifscCode) {
//        this.ifscCode = ifscCode;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getBranchName() {
//        return branchName;
//    }
//
//    public void setBranchName(String branchName) {
//        this.branchName = branchName;
//    }
//
//    public String getEmailId() {
//        return emailId;
//    }
//
//    public void setEmailId(String emailId) {
//        this.emailId = emailId;
//    }
//
//    @Override
//    public String toString() {
//        return "UserDetailsImpl{" +
//                "name='" + name + '\'' +
//                ", password='" + password + '\'' +
//                ", accountnumber=" + accountnumber +
//                ", ifscCode='" + ifscCode + '\'' +
//                ", address='" + address + '\'' +
//                ", branchName='" + branchName + '\'' +
//                ", emailId='" + emailId + '\'' +
//                '}';
//    }
//
//    public UserDetailsImpl(long id,String name, String password, long accountnumber, String ifscCode, String address, String branchName, String emailId) {
//        this.id = id;
//        this.name = name;
//        this.password = password;
//        this.accountnumber = accountnumber;
//        this.ifscCode = ifscCode;
//        this.address = address;
//        this.branchName = branchName;
//        this.emailId = emailId;
//    }
//
//    private String ifscCode;
//    private String address;
//    private String branchName;
//    private String emailId;
//
//    public static UserDetailsImpl build(Customer customer)
//    {
//        return new UserDetailsImpl(customer.getId(),customer.getName(),customer.getPassword(),customer.getAccountNumber(),customer.getIfscCode(),
//                customer.getAddress(),customer.getBranchName(),customer.getEmailId());
//    }
//
//
//
//}
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import com.zscalerlabsession.zscalerlabsession.Model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {

    static final Logger log = LoggerFactory.getLogger(UserDetailsImpl.class);

    private static final long serialVersionUID = 1L;

    private long uid;

    private String name;

    private String email;

    private String phoneNo;
    @JsonIgnore
    private String password;

    private long accountNumber;

    public String getBranchname() {
        return branchname;
    }

    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }

    private String branchname;

    public UserDetailsImpl(long uid, String name, String email, String phoneNo, String password, long accountNumber, Collection<? extends GrantedAuthority> authorities) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.password = password;
        this.accountNumber = accountNumber;
        this.authorities = authorities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl build(Customer user) {
        log.info("****Inside UserDetailsImpl build method***");

        List<GrantedAuthority> authorities=new LinkedList<>();
        log.info(" After authoritiesList-------");
        authorities.add(new SimpleGrantedAuthority("user_role"));
        log.info("Authorities-----"+authorities);
        log.info(" Before build of UserDetailsImpl");
        return new UserDetailsImpl(user.getId(),user.getName(),user.getEmailId(),user.getPhoneNumber(),user.getPassword(),
                  user.getAccountNumber(),authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(uid, user.uid);
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return null;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public static Logger getLog() {
        return log;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

