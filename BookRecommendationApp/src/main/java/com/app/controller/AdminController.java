package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.BookException;
import com.app.model.Book;
import com.app.service.BookService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private BookService bookService;
	
	@PostMapping("/addBook")
//	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Book> addBookhandler(@RequestBody Book book){
		Book addedBook = bookService.addNewBook(book);
		return new ResponseEntity<Book>(addedBook,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/deleteBook/{bookId}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Book> deleteBookHandler(@PathVariable("bookId") Integer bookId) throws BookException{
		Book deletedBook = bookService.deleteBookById(bookId);
		return new ResponseEntity<Book>(deletedBook,HttpStatus.ACCEPTED);
	}
}
