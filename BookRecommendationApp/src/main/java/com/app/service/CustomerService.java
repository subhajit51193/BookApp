package com.app.service;
import java.util.List;

import org.springframework.security.core.Authentication;

import com.app.exception.AuthorException;
import com.app.exception.BookException;
import com.app.exception.CustomerException;
import com.app.exception.ReviewException;
import com.app.model.Author;
import com.app.model.Customer;
import com.app.model.Review;

public interface CustomerService {
	
	public Customer registerCustomer(Customer customer);
	
	public Customer getCustomerDetailsByEmail(String email)throws CustomerException;
	
	public List<Customer> getAllCustomerDetails()throws CustomerException;

	public Customer getMyDetails()throws CustomerException;
	
	public List<Customer> getCustomersBasedOnBook(Integer bookId)throws BookException;
	
	public List<Author> followAuthor(Integer authorId) throws AuthorException,CustomerException;
	
	public List<Review> getAllMyReviews() throws CustomerException,ReviewException;
	
	public List<Author> getAllFollowedAuthors() throws CustomerException,AuthorException;
}
