package com.vignesh.ExpenseManager.Expense;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vignesh.ExpenseManager.User.UserRepository;

@RestController
public class ExpenseController {
	
	@Autowired
	private ExpenseRepository expenseRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping(path="/expenses")
	public ResponseEntity<List<Expense>> getExpenses(){
		return new ResponseEntity<List<Expense>>(expenseRepository.findAll(),HttpStatus.OK);
	}
}
