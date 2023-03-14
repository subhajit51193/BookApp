package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.Book;
import com.app.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer>{

	public Optional<Genre> findByGenreName(String genreName);

//	public boolean findByGenreName(String genreName);
	
	@Query("select books b from Genre where genreId=?1")
	public List<Book> getBooksByGenreId(Integer genreId);
}
