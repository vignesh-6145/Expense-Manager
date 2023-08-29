package com.vignesh.ExpenseManager.User;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vignesh.ExpenseManager.Exceptions.UserNotFoundException;
import com.vignesh.ExpenseManager.Expense.Expense;

@RestController
public class UserController {

	private UserRepository userRepository;
	
	public User createUser(User user){
		int userId = user.getUserId();
		Link selfLink = linkTo(UserController.class).slash(userId).withSelfRel();
		Link allUsersLink = linkTo(methodOn(UserController.class).getAllUsers()).withRel("all-users");
		user.add(selfLink);
		user.add(allUsersLink);
		return user;
	}
	
	@Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@GetMapping(path="/users")
	public CollectionModel<User> getAllUsers(){
		List<User> users = userRepository.findAll().stream().map(user -> createUser(user)).toList();
//		users.replaceAll(this::createUser);	// appending links to each user one by one 
		CollectionModel<User> usersWithHyperlinks = CollectionModel.of(users);
		return usersWithHyperlinks;
	}
	
	@GetMapping(path="/users/{userId}")
	public User getUser(@PathVariable int userId ) {
		User user = userRepository.findById(userId).orElse(null);
		if(user==null)
			throw new UserNotFoundException(String.format("id %d was not found in our records", userId));			
		return createUser(userRepository.findById(userId).get());
	}
	
	@GetMapping(path="/users/{userId}/expenses")
	public ResponseEntity<List<Expense>> getUserExpense(@PathVariable int userId){
		Optional<User> user = userRepository.findById(userId);
		System.out.println(user.get().getExpenses());
		if(user.isEmpty())
			throw new UserNotFoundException(String.format("User with Id : %d not found in our records", userId));
		return new ResponseEntity<List<Expense>> (user.get().getExpenses(),HttpStatus.OK);
		
	}
}
