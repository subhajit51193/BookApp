package com.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.app.exception.BookException;
import com.app.exception.CustomerException;
import com.app.model.Book;
import com.app.model.Customer;
import com.app.model.Review;
import com.app.repository.BookRepository;
import com.app.repository.CustomerRepository;
import com.app.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Override
	public Review addReview(Review review,Integer bookId) throws BookException {
		
		Optional<Customer> opt = customerRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		System.out.println(opt.get());
		if (opt.isEmpty()) {
			throw new CustomerException("Not found");
		}
		else {
			Customer customer = opt.get();
			Optional<Book> optBook = bookRepository.findById(bookId);
			if (optBook.isEmpty()) {
				throw new BookException("Book not found");
			}
			else {
				Book book = optBook.get();
//				----------------
//				updating needs to be done right now it is saving every instance
//				solution - change to bidirectional maping book and review , customer and review;
				
//				----------------
//				Review newReview = reviewRepository.save(review);
//				book.getReviews().add(newReview);
//				customer.getMyReviews().add(newReview);
//				bookRepository.save(book);
//				customerRepository.save(customer);
//				return newReview;
				
//				have to check if it is working or not
				Optional<Review> optR = reviewRepository.getReviewFromCustomerIdAndBookId(customer.getCustId(), bookId);
				if (optR.isEmpty()) {
					review.setBook(book);
					review.setCustomer(customer);
					Review newReview = reviewRepository.save(review);
					book.getReviews().add(newReview);
					customer.getMyReviews().add(newReview);
					bookRepository.save(book);
					customerRepository.save(customer);
					return newReview;
				}
				else {
					Review foundReview = optR.get();
					foundReview.setDescription(review.getDescription());
					foundReview.setRating(review.getRating());
					foundReview.setSubject(review.getSubject());
					
					return reviewRepository.save(foundReview);
				}
				
				
				
				
				
			}
		}
	}

}
