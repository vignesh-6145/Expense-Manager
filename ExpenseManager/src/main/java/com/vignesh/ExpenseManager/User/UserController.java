package com.vignesh.ExpenseManager.User;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vignesh.ExpenseManager.Exceptions.InvalidActionException;
import com.vignesh.ExpenseManager.Exceptions.InvalidPasswordException;
import com.vignesh.ExpenseManager.Exceptions.UserNotFoundException;
import com.vignesh.ExpenseManager.Expense.Expense;
import com.vignesh.ExpenseManager.Expense.ExpenseRepository;

import jakarta.validation.Valid;

@RestController
public class UserController {
	
	private UserRepository userRepository;
	private ExpenseRepository expenseRepository;
	
	public User createUser(User user){
		int userId = user.getUserId();
		Link selfLink = linkTo(UserController.class).slash(userId).withSelfRel();
		Link allUsersLink = linkTo(methodOn(UserController.class).getAllUsers()).withRel("all-users");
		user.add(selfLink);
		user.add(allUsersLink);
		return user;
	}
	
	public EntityModel<Expense> createExpense(Expense expense){
		EntityModel<Expense> expenseL = EntityModel.of(expense);
		int expenseId = expenseL.getContent().getId();
		int userId = expenseL.getContent().getUser().getUserId();
		Link selfLink = linkTo(UserController.class).slash(userId).slash("expenses").slash(expenseId).withSelfRel();
		Link crrUser = linkTo(methodOn(UserController.class).getUser(userId)).withRel("user");
		Link allExpensesLink = linkTo(UserController.class).slash(userId).slash("expenses").withRel("all-expenses");
		expenseL.add(crrUser,selfLink,allExpensesLink);
		return expenseL;
	}
	
	@Autowired
	public UserController(UserRepository userRepository,ExpenseRepository expenseRepository) {
		this.userRepository = userRepository;
		this.expenseRepository = expenseRepository;

	}
	
	@GetMapping(path="/users")
	public ResponseEntity<CollectionModel<User>> getAllUsers(){
		List<User> users = userRepository.findAll().stream().map(user -> createUser(user)).toList();
//		users.replaceAll(this::createUser);	// appending links to each user one by one 
		CollectionModel<User> usersWithHyperlinks = CollectionModel.of(users);
//		return usersWithHyperlinks;
		return new ResponseEntity<CollectionModel<User>>(usersWithHyperlinks,HttpStatus.OK);
	}
	
	@PutMapping(path="/updateUser")
	// Can update any field except 
	// 		password	-	A Separate method should be implemented with special validations
	//		DOJ			-	User has no control over this field	
	public ResponseEntity<User> updateUser(@Valid @RequestBody User user) {
		System.out.println(user);
		User existingInfo = userRepository.findById(user.getUserId()).orElse(null);
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
		
		userRepository.save(existingInfo);
//		return user;
		return new ResponseEntity<User>(existingInfo,HttpStatus.OK);
	}
	


	@PostMapping(path="/users/{userId}/addExpense")
	public ResponseEntity addUserExpense(@PathVariable int userId, @Valid @RequestBody Expense expense) {
		Optional<User> user = userRepository.findById(userId);
		if(user.isEmpty())
			throw new UserNotFoundException(String.format("User with Id : %d not found in our records", userId));
		expense.setUser(user.get());
		expense = expenseRepository.save(expense);
//		System.out.println();
		
		return ResponseEntity.created(linkTo(methodOn(UserController.class).getUser(user.get().getUserId())).slash("expenses").slash(expense.getId()).toUri()).build();
  }
  @GetMapping(path="/users/{userId}")
	public ResponseEntity<User> getUser(@PathVariable int userId ) {
		User user = userRepository.findById(userId).orElse(null);
		if(user==null)
			throw new UserNotFoundException(String.format("id %d was not found in our records", userId));			
		user = createUser(userRepository.findById(userId).get());
		return new ResponseEntity<User>(user,HttpStatus.OK);

	}
}
