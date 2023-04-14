package com.app.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
//@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer genreId;
	
//	@UniqueElements
	private String genreName;
	
//	private String description;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "genres")
	private Set<Book> books = new HashSet<>();
	
	
	
}
