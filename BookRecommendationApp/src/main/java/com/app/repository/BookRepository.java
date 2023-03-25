package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.Book;
import com.app.model.Customer;

public interface BookRepository extends JpaRepository<Book, Integer>{

	@Query("select customers from Book where bookId=?1")
	public List<Customer> findCustomersByBookId(Integer bookId);
}
