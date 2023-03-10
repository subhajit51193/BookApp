package com.app.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.BookException;
import com.app.model.Author;
import com.app.model.Book;
import com.app.model.EGenre;
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
		Set<Genre> genres = new HashSet<>();
		Set<String> all = book.getGetGenres();
		for (String strGenre: all) {
			if(strGenre.equalsIgnoreCase("Biography")) {
				Genre genre = genreRepository.findByGenreName(EGenre.Biography)
						.orElseThrow(()-> new RuntimeException("Error: Genre Not found"));
				genres.add(genre);
			}
			else if(strGenre.equalsIgnoreCase("Science")) {
				Genre genre = genreRepository.findByGenreName(EGenre.Science)
						.orElseThrow(()-> new RuntimeException("Error: Genre Not found"));
				genres.add(genre);
			}
			else if(strGenre.equalsIgnoreCase("Technology")) {
				Genre genre = genreRepository.findByGenreName(EGenre.Technology)
						.orElseThrow(()-> new RuntimeException("Error: Genre Not found"));
				genres.add(genre);
			}
			else if(strGenre.equalsIgnoreCase("Entrepreneurs")) {
				Genre genre = genreRepository.findByGenreName(EGenre.Entrepreneurs)
						.orElseThrow(()-> new RuntimeException("Error: Genre Not found"));
				genres.add(genre);
			}
			else if(strGenre.equalsIgnoreCase("Business")) {
				Genre genre = genreRepository.findByGenreName(EGenre.Business)
						.orElseThrow(()-> new RuntimeException("Error: Genre Not found"));
				genres.add(genre);
			}
			else if(strGenre.equalsIgnoreCase("Leadership")) {
				Genre genre = genreRepository.findByGenreName(EGenre.Leadership)
						.orElseThrow(()-> new RuntimeException("Error: Genre Not found"));
				genres.add(genre);
			}
			else if(strGenre.equalsIgnoreCase("History")) {
				Genre genre = genreRepository.findByGenreName(EGenre.History)
						.orElseThrow(()-> new RuntimeException("Error: Genre Not found"));
				genres.add(genre);
			}
			else if(strGenre.equalsIgnoreCase("Nonfiction")) {
				Genre genre = genreRepository.findByGenreName(EGenre.Nonfiction)
						.orElseThrow(()-> new RuntimeException("Error: Genre Not found"));
				genres.add(genre);
			}
			else if(strGenre.equalsIgnoreCase("Autobiography")) {
				Genre genre = genreRepository.findByGenreName(EGenre.Autobiography)
						.orElseThrow(()-> new RuntimeException("Error: Genre Not found"));
				genres.add(genre);
			}
		}
		book.setGenres(genres);
		Author author = book.getAuthor();
		author.getPublishedBooks().add(book);
		authorRepository.save(author);
		Book savedBook = bookRepository.save(book);
		return savedBook;
		
		
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
