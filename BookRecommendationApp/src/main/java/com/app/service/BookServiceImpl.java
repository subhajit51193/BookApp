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
//		
		Book newBook = new Book();
		
		
		

		
		newBook.setBookName(book.getBookName());
		newBook.setDescription(book.getDescription());
		newBook.setPages(book.getPages());
		newBook.setPublishDate(LocalDate.now());
		
		Author author = book.getAuthor();

		newBook.setAuthor(author);

		
		
		Set<Genre> genres = book.getGenres();
		for (Genre genre: genres) {
//			-----------------------------
			Optional<Genre> opt = genreRepository.findByGenreName(genre.getGenreName());
			if (opt.isEmpty()) {
				newBook.getGenres().add(genre);
				genre.getBooks().add(newBook);
				genreRepository.save(genre);
			}
			else {
				Genre existinggenre = opt.get();
				newBook.getGenres().add(existinggenre);
				existinggenre.getBooks().add(newBook);
				genreRepository.save(existinggenre);
			}
			
//			-----------------
//			newBook.getGenres().add(genre);
//			genre.getBooks().add(newBook);
//			genreRepository.save(genre);
		}
		
		author.getPublishedBooks().add(newBook);
		authorRepository.save(author);
		
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
