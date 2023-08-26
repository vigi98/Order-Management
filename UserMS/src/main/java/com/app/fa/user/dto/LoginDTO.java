package com.app.fa.user.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class LoginDTO {

	@NotNull(message = "{Email cannot Be Null}")
	@Pattern(regexp="^[0-9A-Za-z]+@[a-zA-z]+\\.com$",message = "Please enter valid email")
	private String email;
	private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "LoginDTO [email=" + email + ", password=" + password + "]";
	}
	
	
	
}
