package com.app.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	@OneToMany(mappedBy = "book")
	private Set<Genre> genres;
	
	@ManyToMany(mappedBy = "books")
	private Set<Customer> customers;
	
	@ManyToOne
	private Author author;

	@Override
	public int hashCode() {
		return Objects.hash(author, bookId, bookName, description, pages, publishDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(author, other.author) && Objects.equals(bookId, other.bookId)
				&& Objects.equals(bookName, other.bookName) && Objects.equals(description, other.description)
				&& Objects.equals(pages, other.pages) && Objects.equals(publishDate, other.publishDate);
	}
	
	
}
