package com.codingdojo.project.bookclub.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.project.bookclub.models.LoginUser;
import com.codingdojo.project.bookclub.models.User;
import com.codingdojo.project.bookclub.repositeries.UserRepo;


@Service
public class UserService {
	@Autowired
	 private UserRepo userRepo;
	 
	//retrieving all users
	 public List<User> allUsers() {
			return userRepo.findAll();
		}
		
	 //finding a user by id
	 public User findUser(Long id) {
		        Optional<User> optionalUser = userRepo.findById(id);
		        if(optionalUser.isPresent()) {
		            return optionalUser.get();
		        } else {
		            return null;
		        }
		    }
	 
	 // get the list of users for this song
	 // public List<User> getAssignedSongs(Song song){
	//	 return userRepo.findAllBySongs(song);
	 // }
	 
	 //register method
	 public User register(User newUser, BindingResult result) {
		 Optional<User> potentialUser = userRepo.findByEmail(newUser.getEmail());
		 if(potentialUser.isPresent()) {
			 result.rejectValue("email", "Matches", "The email is already taken");
		 }
		 if(!newUser.getPassword().equals(newUser.getConfirm())) {
			 result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
		 }
		 if(result.hasErrors()) {
			 return null;
		 } 
		 String hashedpw = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		 newUser.setPassword(hashedpw);
		 return userRepo.save(newUser);
		 }

	 //login method for the user 
	 public User login(LoginUser newLoginObject, BindingResult result) {
		 Optional<User> potentialUser = userRepo.findByEmail(newLoginObject.getEmail());
		 if (!potentialUser.isPresent()) {
			 result.rejectValue("email", "Matches", "User not found!");
			 return null;
		 }
		 User user = potentialUser.get();
		 if(!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
			    result.rejectValue("password", "Matches", "Invalid Password!");
			}
		 if (result.hasErrors()) {
			 return null;
		 }
		 return user;
	    }
}
