package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.Dashboard;
import com.app.exception.AuthorException;
import com.app.exception.BookException;
import com.app.exception.CustomerException;
import com.app.exception.ReviewException;
import com.app.model.Author;
import com.app.model.Book;
import com.app.model.Customer;
import com.app.model.Review;
import com.app.service.BookService;
import com.app.service.CustomerService;
import com.app.service.ReviewService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	
		
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

//	-----------------------------------------
	@Autowired
	private BookService bookService;
	
	@Autowired
	private ReviewService reviewService;
//-------------------------------------------
	
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
	
//	-------------------------------------------------------------------------------
//	-------------------------------------------------------------------------------
//	-------------------------------------------------------------------------------
	
	
	@GetMapping("/getBooksByGenre/{genreId}")
	public ResponseEntity<List<Book>> getBooksByGenrehandler(@PathVariable("genreId") Integer genreId) throws BookException{
		List<Book> books = bookService.getAllBooksByGenreId(genreId);
		return new ResponseEntity<List<Book>>(books,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getBooksByAuthor/{authorId}")
	public ResponseEntity<List<Book>> getBooksByAuthorhandler(@PathVariable("authorId") Integer authorId) throws BookException{
		List<Book> books = bookService.getAllBooksByAuthorId(authorId);
		return new ResponseEntity<List<Book>>(books,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/myDetails")
	public ResponseEntity<Customer> getMyDetailsHandler(){
		Customer customer = customerService.getMyDetails();
		return new ResponseEntity<Customer>(customer,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/addBookToMyList/{bookId}")
	public ResponseEntity<Book> addBookToMyListHandler(@PathVariable("bookId") Integer bookId) throws BookException{
		Book book = bookService.addBookToMyList(bookId);
		return new ResponseEntity<Book>(book,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/followAuthor/{authorId}")
	public ResponseEntity<List<Author>> followAuthorHandler(@PathVariable("authorId") Integer authorId) throws CustomerException, AuthorException{
		List<Author> list = customerService.followAuthor(authorId);
		return new ResponseEntity<List<Author>>(list,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/giveReview/{bookId}")
	public ResponseEntity<Review> giveReviewhandler(@RequestBody Review review,@PathVariable("bookId") Integer bookId) throws BookException{
		Review newReview = reviewService.addReview(review, bookId);
		return new ResponseEntity<Review>(newReview,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/myReviews")
	public ResponseEntity<List<Review>> getAllMyReviewsHandler() throws CustomerException, ReviewException{
		
		List<Review> myReviews = customerService.getAllMyReviews();
		return new ResponseEntity<List<Review>>(myReviews,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/myFollowedAuthors")
	public ResponseEntity<List<Author>> getAllFollowedAuthorshandler() throws CustomerException, AuthorException{
		
		List<Author> authors = customerService.getAllFollowedAuthors();
		return new ResponseEntity<List<Author>>(authors,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/myDashboard")
	public ResponseEntity<Dashboard> getMyDashboardHandler()throws CustomerException{
		
		Dashboard dashboard = customerService.getMyDashboard();
		return new ResponseEntity<Dashboard>(dashboard,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/myReviews/desc")
	public ResponseEntity<List<Review>> sortReviewDescHandler() throws CustomerException, ReviewException{
		List<Review> reviews = customerService.getReviewSortedDesc();
		return new ResponseEntity<List<Review>>(reviews,HttpStatus.ACCEPTED);
	}
	
}
