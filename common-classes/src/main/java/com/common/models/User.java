package com.common.models;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@MappedSuperclass
public abstract class User {
	
	@NotBlank(message = "Name is mandatory")
	@Size(min=3, max=10,message = "Name should have minimum 3 character and maximum 10 characters")
	private String goodName;
	
	@NotBlank(message = "Email is mandatory")
	@Email(message="Enter valid email id")
	@Size(max=50, message="Incorrect Email length")
	private String email;
	
	@NotBlank(message = "Password is mandatory")
	private String password;
	
	@NotBlank(message = "Mobile number is mandatory")
	@Size(max=10, message="Mobile number should be 10 digit without country code")
	private String mobile;
	
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
