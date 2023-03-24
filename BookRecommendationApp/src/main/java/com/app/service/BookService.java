package com.app.service;

import java.util.List;

import com.app.exception.BookException;
import com.app.model.Book;

public interface BookService {

	public Book addNewBook(Book book);
	
	public Book deleteBookById(Integer bookId) throws BookException;
	
	public List<Book> getAllBooksByGenreId(Integer genreId) throws BookException;
	
	public List<Book> getAllBooksByAuthorId(Integer authorId) throws BookException;
	
	public Book addBookToMyList(Integer bookId) throws BookException;
}
