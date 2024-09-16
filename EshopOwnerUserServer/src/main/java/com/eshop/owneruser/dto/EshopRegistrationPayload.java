package com.eshop.owneruser.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EshopRegistrationPayload {

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
	
	@NotBlank(message = "E-shop name is mandatory")
	@Size(min = 5, max = 20, message = "E-shop name should be min 5 max 20 character long")
	private String eshopName;
	
	@NotBlank(message = "E-shop search tags are mandatory")
	@Size(min = 10, max = 500, message = "E-shop search tags should be min 10 max 500 character long")
	private String eshopSearchTags;
	
	@NotBlank(message = "E-shop description is mandatory")
	@Size(min = 10, max = 500, message = "E-shop description should be min 10 max 500 character long")
	private String eshopDescription;
	
	@NotNull( message = "Cover Image is mandatory to create e-shop." ) private MultipartFile eshopCoverImage;

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

	public String getEshopName() {
		return eshopName;
	}

	public void setEshopName(String eshopName) {
		this.eshopName = eshopName;
	}

	public String getEshopSearchTags() {
		return eshopSearchTags;
	}

	public void setEshopSearchTags(String eshopSearchTags) {
		this.eshopSearchTags = eshopSearchTags;
	}

	public String getEshopDescription() {
		return eshopDescription;
	}

	public void setEshopDescription(String eshopDescription) {
		this.eshopDescription = eshopDescription;
	}

	public MultipartFile getEshopCoverImage() {
		return eshopCoverImage;
	}

	public void setEshopCoverImage(MultipartFile eshopCoverImage) {
		this.eshopCoverImage = eshopCoverImage;
	}
	
}
