package com.app.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookId;
	
	private String bookName;
	
	private Integer pages;
	
	private LocalDate publishDate;
	
	private String description;
	
//	@ManyToMany(mappedBy = "books",fetch = FetchType.LAZY)
	@ManyToMany
	@JoinTable(
			name = "book_genre",
			joinColumns = @JoinColumn(name = "bookId"),
			inverseJoinColumns = @JoinColumn(name = "genreId"))
	private Set<Genre> genres = new HashSet<>();
	
	private Set<String> getGenres;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "books",fetch = FetchType.LAZY)
	private List<Customer> customers = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "authorId")
	private Author author;

	
	
	
}
