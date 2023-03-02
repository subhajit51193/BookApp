package com.app.service;

import com.app.exception.BookException;
import com.app.model.Book;

public class BookServiceImpl implements BookService{

	@Override
	public Book addNewBook(Book book) throws BookException {
		return book;
		
	}

	
}
