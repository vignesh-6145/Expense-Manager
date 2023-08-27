package com.vignesh.ExpenseManager.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

@Entity(name="users")
public class User {
	@Id
	@GeneratedValue
	private int userId;
	
	@NotNull(message="User Name Can't be null")
	@Column(name="UserName")
	private String userName;
	
	@Column(name="dob")
	@Past(message="Date Of Birth should be in the past")
	private LocalDate dob;
	
	@Column(name="OpeningAmount")
	@PositiveOrZero(message="Please start with atleast zero balance")
	private double openingAmount;
	
	@Column(name="password")
	@NotNull(message="Password can't be null")
	@JsonIgnore		//Password can't be shown to all hence not including in the response
	private String password;
	
	@Column(name="Email")
	@NotNull(message="Email can't be null")
	@Email(message="Please enter a valid Email ex:- abc@gmail.com")
	private String email;
	
//	@Column(name="friends")
//	private List<User> friends;
	
	@Enumerated(EnumType.STRING)
	@Column(name="role")
	@JsonIgnore			
	// role was added from the backend based on UI console and not be shown publicly 
	// hence not including in the response
	
	private UserRoles role;
	
	@Column(name="doj")
	private LocalDateTime doj;
	public User(int userId, @NotNull(message = "User Name Can't be null") String userName,
			@Past(message = "Date Of Birth should be in the past") LocalDate dob,
			@PositiveOrZero(message = "Please start with atleast zero balance") double openingAmount,
			@NotNull(message = "Password can't be null") String password,
			@NotNull(message = "Email can't be null") @Email(message = "Please enter a valid Email ex:- abc@gmail.com") String email,
			/*List<User> friends,*/ UserRoles role, LocalDateTime doj) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.dob = dob;
		this.openingAmount = openingAmount;
		this.password = password;
		this.email = email;
//		this.friends = friends;
		this.role = role;
		this.doj = doj;
	}
	public User() {
		super();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public double getOpeningAmount() {
		return openingAmount;
	}

	public void setOpeningAmount(double openingAmount) {
		this.openingAmount = openingAmount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	public List<User> getFriends() {
//		return friends;
//	}

//	public void setFriends(List<User> friends) {
//		this.friends = friends;
//	}

	public UserRoles getRole() {
		return role;
	}

	public void setRole(UserRoles role) {
		this.role = role;
	}

	public LocalDateTime getDoj() {
		return doj;
	}

	public void setDoj(LocalDateTime doj) {
		this.doj = doj;
	}
	
}
