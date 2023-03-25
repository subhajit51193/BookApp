package com.app.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.app.exception.BookException;
import com.app.exception.CustomerException;
import com.app.model.Author;
import com.app.model.Book;
import com.app.model.Customer;
import com.app.model.Genre;
import com.app.repository.AuthorRepository;
import com.app.repository.BookRepository;
import com.app.repository.CustomerRepository;
import com.app.repository.GenreRepository;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private GenreRepository genreRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
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

	@Override
	public List<Book> getAllBooksByGenreId(Integer genreId) throws BookException {
		
		List<Book> list =genreRepository.getBooksByGenreId(genreId);
		if (list.isEmpty()) {
			throw new BookException("No book found");
		}
		else {
			return list;
		}
	}

	@Override
	public List<Book> getAllBooksByAuthorId(Integer authorId) throws BookException {
		
		List<Book> list = authorRepository.getBooksByAuthorId(authorId);
		if (list.isEmpty()) {
			throw new BookException("No Book found");
		}
		else {
			return list;
		}
	}

	@Override
	public Book addBookToMyList(Integer bookId) throws BookException {
		
		
		
		Optional<Customer> opt = customerRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		Customer customer = opt.get();
		System.err.println(customer.getName());
		Optional<Book> bookOpt = bookRepository.findById(bookId);
		if (bookOpt.isEmpty()) {
			throw new BookException("Book Not found...");
		}
		else {
			Book book = bookOpt.get();
			System.out.println(book.getBookName());
			customer.getBooks().add(book);
			book.getCustomers().add(customer);
			bookRepository.save(book);
			customerRepository.save(customer);
			return book;
		}
		
		
	}

	
}
