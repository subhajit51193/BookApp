package com.app.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.app.exception.AuthorException;
import com.app.exception.BookException;
import com.app.exception.CustomerException;
import com.app.exception.ReviewException;
import com.app.model.Author;
import com.app.model.Authority;
import com.app.model.Book;
import com.app.model.Customer;
import com.app.model.Review;
import com.app.repository.AuthorRepository;
import com.app.repository.BookRepository;
import com.app.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	
	
	@Override
	public Customer registerCustomer(Customer customer) throws CustomerException {
		
		List<Authority> authorities= customer.getAuthorities();
		
		for(Authority authority:authorities) {
			authority.setCustomer(customer);
		}
		
		return customerRepository.save(customer);
		
		
	}

	@Override
	public Customer getCustomerDetailsByEmail(String email)throws CustomerException {
		
		return customerRepository.findByEmail(email).orElseThrow(() -> new CustomerException("Customer Not found with Email: "+email));
	}

	@Override
	public List<Customer> getAllCustomerDetails()throws CustomerException {
		
		List<Customer> customers= customerRepository.findAll();
		
		if(customers.isEmpty())
			throw new CustomerException("No Customer find");
		
		return customers;
		
	}

	@Override
	public Customer getMyDetails() throws CustomerException {
		
		Optional<Customer> opt = customerRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		System.out.println(opt.get());
		if (opt.isEmpty()) {
			throw new CustomerException("Not found");
		}
		else {
			return opt.get();
		}
	}

	@Override
	public List<Customer> getCustomersBasedOnBook(Integer bookId) throws BookException {
		
		List<Customer> customers = bookRepository.findCustomersByBookId(bookId);
		if (customers.isEmpty()) {
			throw new BookException("No customers found who shortlisted this book");
		}
		else {
			return customers;
		}
	}

	@Override
	public List<Author> followAuthor(Integer authorId) throws AuthorException,CustomerException {
		
		Optional<Customer> opt = customerRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		if (opt.isEmpty()) {
			throw new CustomerException("Not found");
		}
		else {
			Customer customer =  opt.get();
			Optional<Author> opt2 = authorRepository.findById(authorId);
			if (opt2.isEmpty()) {
				throw new AuthorException("Author not found");
			}
			else {
				Author author = opt2.get();
				List<Author> authors = customer.getAuthors();
				authors.add(author);
				author.getCustomers().add(customer);
				author.setFollowers((long)author.getCustomers().size());
				authorRepository.save(author);
				customerRepository.save(customer);
				return authors;
			}
			
		}
		
	}

	@Override
	public List<Review> getAllMyReviews() throws CustomerException, ReviewException {
		Optional<Customer> opt = customerRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		if (opt.isEmpty()) {
			throw new CustomerException("Not found");
		}
		else {
			Customer customer =  opt.get();
			List<Review> myReviews = customerRepository.getAllReviewsByCustId(customer.getCustId());
			if (myReviews.isEmpty()) {
				throw new ReviewException("List is empty");
			}
			else {
				return myReviews;
			}
			
			
		}
	}

	@Override
	public List<Author> getAllFollowedAuthors() throws CustomerException, AuthorException {
		
		Optional<Customer> opt = customerRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		if (opt.isEmpty()) {
			throw new CustomerException("Not found");
		}
		else {
			Customer customer =  opt.get();
			List<Author> authors = customerRepository.getAllAuthorsByCustId(customer.getCustId());
			if (authors.isEmpty()) {
				throw new AuthorException("List is empty");
			}
			else {
				return authors;
			}
			
			
		}
	}



}
