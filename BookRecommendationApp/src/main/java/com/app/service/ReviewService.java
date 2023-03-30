package com.app.service;

import com.app.exception.BookException;
import com.app.model.Review;

public interface ReviewService {

	public Review addReview(Review review,Integer bookId) throws BookException;
}
