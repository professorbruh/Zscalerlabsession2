package com.zscalerlabsession.zscalerlabsession.controllers;

import com.zscalerlabsession.zscalerlabsession.Model.Customer;
import com.zscalerlabsession.zscalerlabsession.Repository.CustomerRepository;
import com.zscalerlabsession.zscalerlabsession.response.CustomResponseForLogin;
import com.zscalerlabsession.zscalerlabsession.response.CustomResponseForNoUser;
import com.zscalerlabsession.zscalerlabsession.security.JwtUtils;
//import com.zscalerlabsession.zscalerlabsession.security.UserDetailsImpl;
import com.zscalerlabsession.zscalerlabsession.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.zscalerlabsession.zscalerlabsession.service.AuthService;

import java.util.Date;

@RestController
public class LoginController {


    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AuthService authService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;


    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Customer customer)
    {
        Customer fetchCustomer = authService.fetchCustomerByEmail(customer.getEmailId());
        if(fetchCustomer!=null)
        {
            if(encoder.matches(customer.getPassword(), fetchCustomer.getPassword()))
            {
                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(customer.getEmailId(),customer.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = jwtUtils.generateJwtToken(authentication);

                UserDetailsImpl details =(UserDetailsImpl)authentication.getPrincipal();
                if(details != null)
                {
                    System.out.println(customer.getPassword());
                    CustomResponseForLogin response = new CustomResponseForLogin(new Date(),"Login Successfull","200",jwt, fetchCustomer.getName(),customer.getEmailId());
                    return new ResponseEntity<Object>(response, HttpStatus.OK);
                }
                else {
                    CustomResponseForNoUser response = new CustomResponseForNoUser(new Date(),"Error in authenticaion","409");
                    return new ResponseEntity<Object>(response,HttpStatus.OK);

                }

            }else{
                CustomResponseForNoUser response = new CustomResponseForNoUser(new Date(),"Invalid Credentials","409");
                return new ResponseEntity<Object>(response,HttpStatus.OK);
            }
        }
        else
        {
            CustomResponseForNoUser response = new CustomResponseForNoUser(new Date(),"User Not Found","409");
            return new ResponseEntity<Object>(response,HttpStatus.OK);
        }
    }


}
