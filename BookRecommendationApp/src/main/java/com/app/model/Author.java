package com.app.model;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer authorId;
	
	private String authorName;
	
	private Long followers=(long)0;
	
	@OneToMany(mappedBy = "author")
	private List<Book> publishedBooks;
	
	@ManyToMany(mappedBy = "authors")
	private List<Customer> customers;

	

	
	
	
}
