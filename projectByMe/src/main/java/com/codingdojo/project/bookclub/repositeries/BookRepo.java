package com.codingdojo.project.bookclub.repositeries;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.project.bookclub.models.Book;

public interface BookRepo extends CrudRepository<Book, Long> {
	List<Book> findAll();
}
