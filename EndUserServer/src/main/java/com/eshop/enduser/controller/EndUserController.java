package com.eshop.enduser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.models.UpdatePasswordPayload;
import com.eshop.enduser.models.EndUser;
import com.eshop.enduser.service.EndUserService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/endUser")
public class EndUserController {
	
	@Autowired private EndUserService endUserService;

	@PostMapping("/createOrUpdate")
	public ResponseEntity<Object> createOrUpdateUser(@Valid @RequestBody EndUser endUser ){	
		return endUserService.createOrUpdateUser(endUser);
	}
	
	@PutMapping("/updatePassword")
	public ResponseEntity<String> updatePassword(@Valid @RequestBody UpdatePasswordPayload updatePasswordPayload ){
		return endUserService.updatePassword(updatePasswordPayload.getEmail(), 
				updatePasswordPayload.getOldPassword(), 
				updatePasswordPayload.getNewPassword());
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> createOrUpdateUser( Integer userId ){
		return endUserService.deleteUser(userId);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<Object> getAllEndUser(){
		return endUserService.getAllEndUser();
	}
}
