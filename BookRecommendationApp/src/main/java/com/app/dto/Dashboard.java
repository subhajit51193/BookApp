package com.app.dto;

import java.util.ArrayList;
import java.util.List;

import com.app.model.Author;
import com.app.model.Book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Dashboard {

	private Integer following=0;
	private Integer totalBooksAddedtoMyList = 0;
	
	
	
}
