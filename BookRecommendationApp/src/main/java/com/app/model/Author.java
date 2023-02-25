package com.app.model;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private Set<Book> publishedBooks;

	@Override
	public int hashCode() {
		return Objects.hash(authorId, authorName, followers);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		return Objects.equals(authorId, other.authorId) && Objects.equals(authorName, other.authorName)
				&& Objects.equals(followers, other.followers);
	}
	
	
}
