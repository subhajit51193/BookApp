package com.app.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.BookException;
import com.app.model.Author;
import com.app.model.Book;
import com.app.model.Genre;
import com.app.repository.AuthorRepository;
import com.app.repository.BookRepository;
import com.app.repository.GenreRepository;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private GenreRepository genreRepository;
	
	@Override
	public Book addNewBook(Book book) {
//		return book;
		
		List<Genre> genres = book.getGenres();
		for (Genre genre: genres) {
			genre.getBooks().add(book);
			genreRepository.save(genre);
		}
		Author author = book.getAuthor();
		author.getPublishedBooks().add(book);
		authorRepository.save(author);
		Book savedBook = bookRepository.save(book);
		return savedBook;
		
		
		
	}

	
}