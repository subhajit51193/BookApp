package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>{

	@Query("from Review where customer.custId=?1 and book.bookId=?2")
	public Optional<Review> getReviewFromCustomerIdAndBookId(Integer custId,Integer bookId);
}
