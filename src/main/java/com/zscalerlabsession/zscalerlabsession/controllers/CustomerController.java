package com.zscalerlabsession.zscalerlabsession.controllers;


import com.zscalerlabsession.zscalerlabsession.Model.Customer;;
import com.zscalerlabsession.zscalerlabsession.Repository.CustomerRepository;
import com.zscalerlabsession.zscalerlabsession.response.CustomerDetailsResponse;
import com.zscalerlabsession.zscalerlabsession.response.GetCustomerResponse;
import com.zscalerlabsession.zscalerlabsession.security.JwtUtils;
import com.zscalerlabsession.zscalerlabsession.response.CustomResponseForNoUser;

import com.zscalerlabsession.zscalerlabsession.service.AuthService;
import com.zscalerlabsession.zscalerlabsession.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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
	AuthService authService;

	@Autowired
	ValidationService validationService;

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
				return new ResponseEntity<Object>(response,HttpStatus.OK);
			}

		}else {

			CustomResponseForNoUser response = new CustomResponseForNoUser(new Date(),"Email id already exists","409");
			return new ResponseEntity<Object>(response,HttpStatus.OK);

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
		GetCustomerResponse response = new GetCustomerResponse(fetchCustomer);
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
}
