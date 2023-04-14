package com.app.model;

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
import jakarta.persistence.ManyToMany;
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
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer authorId;
	
	private String authorName;
	
	private Long followers=(long)0;
	
	@JsonIgnore
	@OneToMany(mappedBy = "author",fetch = FetchType.LAZY)
	private List<Book> publishedBooks = new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany(mappedBy = "authors",fetch = FetchType.LAZY)
	private List<Customer> customers = new ArrayList<>();

	

	
	
	
}
