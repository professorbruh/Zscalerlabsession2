package com.zscalerlabsession.zscalerlabsession.controllers;


import com.zscalerlabsession.zscalerlabsession.Model.Account;
import com.zscalerlabsession.zscalerlabsession.Model.Customer;
import com.zscalerlabsession.zscalerlabsession.Repository.CustomerRepository;
import com.zscalerlabsession.zscalerlabsession.Request.UpdatePasswordRequest;
import com.zscalerlabsession.zscalerlabsession.Request.UpdateUserRequest;
import com.zscalerlabsession.zscalerlabsession.response.*;
import com.zscalerlabsession.zscalerlabsession.security.JwtUtils;

import com.zscalerlabsession.zscalerlabsession.service.AccountService;
import com.zscalerlabsession.zscalerlabsession.service.AuthService;
import com.zscalerlabsession.zscalerlabsession.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/customers")
@CrossOrigin
public class CustomerController
{
   
	@Autowired
	CustomerRepository customers;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

	@Autowired
	AccountService accountService;

	@Autowired
	AuthService authService;

	@Autowired
	ValidationService validationService;

	@Autowired
	PasswordEncoder encoder;

    @GetMapping("/testing")
    public ResponseEntity<Object> testing(){
		CustomResponseForNoUser response = new CustomResponseForNoUser(new Date(),"Email id already exists","409");
		return new ResponseEntity<Object>(response,HttpStatus.OK);
    }

    @PostMapping("/createCustomer")
	public ResponseEntity<Object> createCustomer(@RequestBody Customer customerDetails){

		Customer fetchAdmin = authService.fetchCustomerByEmail(customerDetails.getEmailId());

		if(fetchAdmin == null) {
			if (validationService.emailValidation(customerDetails)) {
				Customer cust = authService.createCustomer(customerDetails);

				CustomerDetailsResponse response = new CustomerDetailsResponse(new Date(), "Customer Created Succesfully", "200", cust);

				return new ResponseEntity<Object>(response, HttpStatus.OK);
			}
			else
			{
				CustomResponseForNoUser response = new CustomResponseForNoUser(new Date(),"Invalid Email id","409");
				return new ResponseEntity<Object>(response,HttpStatus.UNAUTHORIZED);
			}

		}else {

			CustomResponseForNoUser response = new CustomResponseForNoUser(new Date(),"Email id already exists","409");
			return new ResponseEntity<Object>(response,HttpStatus.UNAUTHORIZED);

		}

	}
	@PostMapping("/getCustomer")
	public ResponseEntity<Object> getCustomer(@RequestBody Customer customerDetails)
	{
		System.out.println(customerDetails.getEmailId());
		Customer fetchCustomer = authService.fetchCustomerByEmail(customerDetails.getEmailId());
		if (fetchCustomer == null)
		{
			CustomResponseForNoUser response = new CustomResponseForNoUser(new Date(),"Error in authentication","409");
			return new ResponseEntity<Object>(response,HttpStatus.OK);
		}
		Account account = accountService.fetchAccountByEmail(customerDetails.getEmailId());
		fetchCustomer.setPassword(null);
		GetCustomerResponse response = new GetCustomerResponse(fetchCustomer,account.getBalance());
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
	@PostMapping("/updatePassword") // Done by Tejesh and Vishwachand
	public String updatePassword(@RequestBody UpdatePasswordRequest Details)
	{
		String emailId = Details.getEmailId();
		String oldPassword = Details.getOldPassword();
		String newPassword = Details.getNewPassword();

		Customer fetchCustomer = authService.fetchCustomerByEmail(emailId);

		if(!encoder.matches(oldPassword, fetchCustomer.getPassword()))
			return "Incorrect Current Password";

		authService.updatePassword(emailId, newPassword);
		return "Update Successful";


	}

	@PostMapping("/updateUser")
	public ResponseEntity<Object> updateUser(@RequestBody UpdateUserRequest user)
	{
		Customer fetchCustomer = authService.fetchCustomerByEmail(user.getEmailId());

		if(!encoder.matches(user.getPassword(), fetchCustomer.getPassword())) {
			CustomerResponseForInvalidLogin response = new CustomerResponseForInvalidLogin("Wrong password", new Date());
			return new ResponseEntity<Object>(response,HttpStatus.UNAUTHORIZED);
		}
		if(user.getAddress()!="")
		{
			fetchCustomer.setAddress(user.getAddress());
		}
		if(user.getName()!="")
		{
			fetchCustomer.setName(user.getName());
		}
		if(user.getPhoneNumber()!="")
		{
			fetchCustomer.setPhoneNumber(user.getPhoneNumber());
		}
		if(user.getEmailId()!="")
		{
			fetchCustomer.setEmailId(user.getEmailId());
		}
		customers.save(fetchCustomer);
		CustomerResponseForInvalidLogin response = new CustomerResponseForInvalidLogin("Success", new Date());
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}

}
