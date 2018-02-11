package com.johnfnash.study.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {
	@NotNull(message = "{username.null}")
	@Size(min = 5, max = 16, message = "{username.size}")
	private String username;

	@NotNull(message = "{password.null}")
	@Size(min = 5, max = 25, message = "{password.size}")
	private String password;

	@Size(min = 2, max = 30, message = "{firstName.size}")
	private String firstName;

	@Size(min = 2, max = 30, message = "{lastName.size}")
	private String lastName;

	@NotBlank
	@Email(message = "{email.valid}")
	private String email;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
