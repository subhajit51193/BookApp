package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer>{

	public Optional<Genre> findByGenreName(String genreName);
}
