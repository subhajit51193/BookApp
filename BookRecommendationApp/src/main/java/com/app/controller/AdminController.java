package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Book> addBookhandler(@RequestBody Book book){
		Book addedBook = bookService.addNewBook(book);
		return new ResponseEntity<Book>(addedBook,HttpStatus.ACCEPTED);
	}
}
