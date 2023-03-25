package com.app.service;
import java.util.List;

import org.springframework.security.core.Authentication;

import com.app.exception.BookException;
import com.app.exception.CustomerException;
import com.app.model.Customer;

public interface CustomerService {
	
	public Customer registerCustomer(Customer customer);
	
	public Customer getCustomerDetailsByEmail(String email)throws CustomerException;
	
	public List<Customer> getAllCustomerDetails()throws CustomerException;

	public Customer getMyDetails()throws CustomerException;
	
	public List<Customer> getCustomersBasedOnBook(Integer bookId)throws BookException;
}
