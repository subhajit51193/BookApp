package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.exception.CustomerException;
import com.app.model.Author;
import com.app.model.Customer;
import com.app.model.Review;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	
	public Optional<Customer> findByEmail(String email);
	
	@Query("select myReviews from Customer where custId=?1")
	public List<Review> getAllReviewsByCustId(Integer id);
	
	@Query("select authors from Customer where custId=?1")
	public List<Author> getAllAuthorsByCustId(Integer id);
}
