package com.codingdojo.project.bookclub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.codingdojo.project.bookclub.models.Book;
import com.codingdojo.project.bookclub.models.LoginUser;
import com.codingdojo.project.bookclub.models.User;
import com.codingdojo.project.bookclub.services.BookService;
import com.codingdojo.project.bookclub.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class MainController {
	@Autowired 
	UserService userServ;
	@Autowired
	BookService bookServ;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "index.jsp";
		
		}
	
	@PostMapping("/register")
	 public String register(
			 @Valid @ModelAttribute("newUser") User newUser, 
			 BindingResult result, Model model,
			 HttpSession session) {
		 	 userServ.register(newUser, result);
		 	
	        if(result.hasErrors()) {
	            model.addAttribute("newLogin", new LoginUser());
	            return "index.jsp";
	        } else {
	        	session.setAttribute("userID", newUser.getId());
	        	return "redirect:/books";
	        }
	 }
	
	 @PostMapping("/login")
	    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
	            BindingResult result,
	            Model model, 
	            HttpSession session) {
	        User user = userServ.login(newLogin, result);
	        
	        if(result.hasErrors()) {
	            model.addAttribute("newUser", new User());
	            return "index.jsp";
	        }
	        session.setAttribute("userID", user.getId());
	        return "redirect:/books";
	 }
	 
	 @GetMapping("/logout")
	    public String logOut(HttpSession session) {
	    	session.setAttribute("userID", null);
	    	return "redirect:/";
	    }
	 @GetMapping("/books")
	 public String dashboard(
			 HttpSession session, 
			 Model model,
			 @ModelAttribute("book") Book book) {
		 if(session.getAttribute("userID") == null) {
	    		return "redirect:/";
	    	}
		 Long UserID = (Long) session.getAttribute("userID");
		 User user = userServ.findUser(UserID);
		 List<Book> books = bookServ.allBooks();
		 model.addAttribute("user", user);
		 model.addAttribute("books", books);
		 return "dashboard.jsp";
	 }
	 
	 @PostMapping("/add")
	 public String addBook(HttpSession session,
			 @Valid @ModelAttribute("book") Book book,
			 BindingResult result) {
		 if(session.getAttribute("userID") == null) {
				return "redirect:/logout";
			}
		 if (result.hasErrors()) {
			 return "dashboard.jsp";
		 }
		 bookServ.createBook(book);
		 return "redirect:/books";
	 }
	
	 @GetMapping("/books/{id}")
	 public String showBook(@PathVariable("id") Long id,
			 HttpSession session,
			 Model model,
			 @ModelAttribute("book") Book book) {
		 if(session.getAttribute("userID") == null) {
				return "redirect:/logout";
			}
		 Long userID = (Long) session.getAttribute("userID");
		 User user = userServ.findUser(userID);
		 Book b = bookServ.findBook(id);
		 model.addAttribute("book", b);
		 model.addAttribute("user", user);
		 return "show.jsp";
	 }
	 
	@PutMapping("edit/{id}")
	public String updateBook(@PathVariable("id") Long id,
			HttpSession session,
			@Valid @ModelAttribute("book") Book book,
			BindingResult result) {
		if(session.getAttribute("userID") == null) {
			return "redirect:/logout";
		}

    	if (result.hasErrors()) {
    		return "show.jsp";
    	}
		Book b = bookServ.findBook(id);
		bookServ.updateBook(book);
		return "redirect:/books";
	}
	
	 @DeleteMapping("/delete/{id}")
	 public String deleteBook(@PathVariable("id") Long id, HttpSession session) {
			
			if(session.getAttribute("userID") == null) {
				return "redirect:/logout";
			}
			bookServ.deleteBook(id);
			return "redirect:/books";
		}
	 
	 @GetMapping("/favour/{id}")
	 public String favourBook(@PathVariable("id") Long id,
			 HttpSession session) {
		 if(session.getAttribute("userID") == null) {
				return "redirect:/logout";
			}
		 
		 Book book = bookServ.findBook(id);
		 Long userID = (Long) session.getAttribute("userID");
		 User user = userServ.findUser(userID);
		 
		 book.getFavouredBy().add(user);
		 bookServ.updateBook(book);
		 
		 return "redirect:/books";
	 }
	 
	 @GetMapping("unfavourite/{id}")
	 public String unfavourBook(@PathVariable("id") Long id, HttpSession session) {
		 if(session.getAttribute("userID") == null) {
				return "redirect:/logout";
			}
		 Book book = bookServ.findBook(id);
		 Long userID = (Long) session.getAttribute("userID");
		 User user = userServ.findUser(userID);
		 
		 book.getFavouredBy().remove(user);
		 bookServ.updateBook(book);
		 return "redirect:/books";
		 
	 }
}
