package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.Author;
import com.app.model.Book;
import com.app.model.Genre;

public interface AuthorRepository extends JpaRepository<Author, Integer>{

	public Optional<Author> findByAuthorName(String authorName);
	
	@Query("select publishedBooks from Author where authorId=?1")
	public List<Book> getBooksByAuthorId(Integer authorId);
	
}
