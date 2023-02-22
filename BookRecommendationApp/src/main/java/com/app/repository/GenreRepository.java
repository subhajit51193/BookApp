package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer>{

}
