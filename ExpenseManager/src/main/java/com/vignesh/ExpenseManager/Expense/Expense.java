package com.vignesh.ExpenseManager.Expense;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vignesh.ExpenseManager.User.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Expense {
	
	@Id
	@GeneratedValue
	int id;
	
	@NotNull(message="Description can't be null")
	@Size(min=5,message="Description can't be less than 5 characters")
	@Column(name="description")
	private String description;
	
	@NotNull(message="Can't create an Expense with out amount")
	@Column(name="amount")
	private double amount;
	
	@ManyToOne(fetch = FetchType.LAZY)
//	@Column(name = "user_id") 
	@JsonIgnore
	private User user;

	public Expense(int id,
			@NotNull(message = "Description can't be null") @Size(min = 5, message = "Description can't be less than 5 characters") String description,
			@NotNull(message = "Can't create an Expense with out amount") double amount, User user) {
		super();
		this.id = id;
		this.description = description;
		this.amount = amount;
		this.user = user;
	} 
	
	public Expense() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Expense [id=" + id + ", description=" + description + ", amount=" + amount + ", user=" + user + "]";
	}
	
	
	
}
