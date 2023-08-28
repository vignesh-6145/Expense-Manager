package com.vignesh.ExpenseManager.User;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vignesh.ExpenseManager.Exceptions.InvalidActionException;
import com.vignesh.ExpenseManager.Exceptions.InvalidPasswordException;
import com.vignesh.ExpenseManager.Exceptions.UserNotFoundException;

import jakarta.validation.Valid;

@RestController
public class UserController {

	private UserRepository repository;
	
	public User createUser(User user){
		int userId = user.getUserId();
		Link selfLink = linkTo(UserController.class).slash(userId).withSelfRel();
		Link allUsersLink = linkTo(methodOn(UserController.class).getAllUsers()).withRel("all-users");
		user.add(selfLink);
		user.add(allUsersLink);
		return user;
	}
	
	@Autowired
	public UserController(UserRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping(path="/users")
	public CollectionModel<User> getAllUsers(){
		List<User> users = repository.findAll().stream().map(user -> createUser(user)).toList();
//		users.replaceAll(this::createUser);	// appending links to each user one by one 
		CollectionModel<User> usersWithHyperlinks = CollectionModel.of(users);
		return usersWithHyperlinks;
	}
	
	@PutMapping(path="/updateUser")
	// Can update any field except 
	// 		password	-	A Separate method should be implemented with special validations
	//		DOJ			-	User has no control over this field	
	public User updateUser(@Valid @RequestBody User user) {
		System.out.println(user);
		User existingInfo = repository.findById(user.getUserId()).orElse(null);
		if(existingInfo==null)
			throw new UserNotFoundException(String.format("id %d was not found in our records", user.getUserId()));
		if(!existingInfo.getPassword().equals(user.getPassword())) {
			throw new InvalidPasswordException("Please Check the password you had entered");
		}
		if(user.getDoj()!=null)
			throw new InvalidActionException("Date Of Joining Can't be modified according our policies");
		
		
		if(user.getDob()!=null)
			existingInfo.setDob(user.getDob());
		if(user.getUserName()!=null)
			existingInfo.setUserName(user.getUserName());
		if(user.getOpeningAmount()!=0)
			existingInfo.setOpeningAmount(user.getOpeningAmount());
		
		repository.save(existingInfo);
		return user;
	}
	
	@GetMapping(path="/users/{userId}")
	public User getUser(@PathVariable int userId ) {
		User user = repository.findById(userId).orElse(null);
		if(user==null)
			throw new UserNotFoundException(String.format("id %d was not found in our records", userId));			
		return createUser(repository.findById(userId).get());
	}
}
