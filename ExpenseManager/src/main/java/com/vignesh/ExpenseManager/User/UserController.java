package com.vignesh.ExpenseManager.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	private UserRepository repository;
	
	@Autowired
	public UserController(UserRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping(path="/users")
	public List<User> getAllUsers(){
		return repository.findAll();
	}
}
