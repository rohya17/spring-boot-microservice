package com.common.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdatePasswordPayload {

	@NotBlank(message = "Email is mandatory")
	private String email;
	@NotBlank(message = "Old password is mandatory")
	@Size(min=6,max=15, message="Incorrect Password length")
	private String oldPassword;
	@NotBlank(message = "New password is mandatory")
	@Size(min=6,max=15, message="Incorrect Password length")
	private String newPassword;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
}
