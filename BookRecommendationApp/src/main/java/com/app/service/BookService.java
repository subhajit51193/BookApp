package com.app.service;

import com.app.exception.BookException;
import com.app.model.Book;

public interface BookService {

	public Book addNewBook(Book book)throws BookException;
}
