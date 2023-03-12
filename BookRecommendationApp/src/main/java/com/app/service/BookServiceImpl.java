package com.app.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
		
		
//creating new book instance to set values manually		
		Book newBook = new Book();
	
		newBook.setBookName(book.getBookName());
		newBook.setDescription(book.getDescription());
		newBook.setPages(book.getPages());
		newBook.setPublishDate(LocalDate.now());
		
		
		Set<Genre> genres = book.getGenres();
		for (Genre genre: genres) {
//			-------checking if genre already exists or not so that we can create new instance or update existing one
			Optional<Genre> opt = genreRepository.findByGenreName(genre.getGenreName());
			if (opt.isEmpty()) {
				//creating new
				newBook.getGenres().add(genre);
				genre.getBooks().add(newBook);
				genreRepository.save(genre);
			}
			else {
				//updating existing one
				Genre existinggenre = opt.get();
				newBook.getGenres().add(existinggenre);
				existinggenre.getBooks().add(newBook);
				genreRepository.save(existinggenre);
			}
			
		}
		
		
//		-------------checking if genre already exists or not so that we can create new instance or update existing one
		Optional<Author> opt1 = authorRepository.findByAuthorName(book.getAuthor().getAuthorName());
		if (opt1.isEmpty()) {
			//creating new
			Author author = book.getAuthor();

			newBook.setAuthor(author);
			author.getPublishedBooks().add(newBook);
			authorRepository.save(author);
		}
		else {
			//updating existing one
			Author existingAuthor = opt1.get();
			newBook.setAuthor(existingAuthor);
			existingAuthor.getPublishedBooks().add(newBook);
			authorRepository.save(existingAuthor);
		}
		
		
		
		return bookRepository.save(newBook);
		
		
	}

	@Override
	public Book deleteBookById(Integer bookId) throws BookException {
		
		Optional<Book> opt = bookRepository.findById(bookId);
		if (opt.isEmpty()) {
			throw new BookException("Book not found...");
		}
		else {
			Book foundBook = opt.get();
			bookRepository.delete(foundBook);
			return foundBook;
		}
	}

	
}
