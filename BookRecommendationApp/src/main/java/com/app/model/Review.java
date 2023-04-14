package com.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer reviewId;
	
	private Integer rating;
	
	private String subject;
	
	private String description;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bookId")
	private Book book;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "custId")
	private Customer customer;
	
	
}
