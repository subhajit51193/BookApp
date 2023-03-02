package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Customer;
import com.app.service.CustomerService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	
		
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@GetMapping("/hello")
	public String testHandler() {
		return "Welcome to Spring Security";
	}
	
	
	/*
	
	 {
        "name": "ram",
        "email": "ram@gmail.com",
        "password": "1234",
        "address": "delhi",
        "authorities":[
            {
                "name": "ROLE_USER"
            },
            {
                "name": "ROLE_ADMIN"
            }
        ]
    }
	
	
	
	*/
	
	@PostMapping("/signUp")
	public ResponseEntity<Customer> saveCustomerHandler(@RequestBody Customer customer){

		
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		
		Customer registeredCustomer= customerService.registerCustomer(customer);
		
		return new ResponseEntity<>(registeredCustomer,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/getCustomer/{email}")
	public ResponseEntity<Customer> getCustomerByEmailHandler(@PathVariable("email") String email){
		
		
		Customer customer= customerService.getCustomerDetailsByEmail(email);
		
		return new ResponseEntity<>(customer,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<Customer>> getAllCustomerHandler(){
		
		
		List<Customer> customers= customerService.getAllCustomerDetails();
		
		return new ResponseEntity<>(customers,HttpStatus.ACCEPTED);
		
	}
	
	
	
}
