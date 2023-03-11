package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Author;
import com.app.model.Genre;

public interface AuthorRepository extends JpaRepository<Author, Integer>{

	public boolean findByAuthorName(String authorName);
	
}
