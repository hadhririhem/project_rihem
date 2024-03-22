package com.codingdojo.project.bookclub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.project.bookclub.models.Book;
import com.codingdojo.project.bookclub.repositeries.BookRepo;

@Service
public class BookService {
	@Autowired
	BookRepo bookRepo;
	
	//retrieves all books
	 public List<Book> allBooks() {
	        return bookRepo.findAll();
	    }
	 
	    // creates a book
	    public Book createBook(Book b) {
	        return bookRepo.save(b);
	    }
	    
	    // retrieves a book
	    public Book findBook(Long id) {
	        Optional<Book> optionalBook = bookRepo.findById(id);
	        if(optionalBook.isPresent()) {
	            return optionalBook.get();
	        } else {
	            return null;
	        }
	    }
	    
	    //updated books
	    public Book updateBook(Book b) {
	    	return bookRepo.save(b);
	    }
	    
	    
	    // deletes book 
	    public void deleteBook(Long id) {
	    	 Optional<Book> optionalBook = bookRepo.findById(id);
	         if(optionalBook.isPresent()) {
	             bookRepo.deleteById(id);
	         }
	    }
}
