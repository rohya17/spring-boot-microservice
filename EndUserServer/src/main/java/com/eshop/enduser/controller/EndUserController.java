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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.models.UpdatePasswordPayload;
import com.eshop.enduser.models.EndUser;
import com.eshop.enduser.service.EndUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@CrossOrigin("*")
@RestController("EndUserController")
@RequestMapping("/endUser")
public class EndUserController {
	
	@Autowired private EndUserService endUserService;

	@Operation(summary = "Creates or Updates the user", description = "creates user object with password, password stored in encrypted format")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "User egistered/updated successfully."), 
	    })
	@PostMapping("/createOrUpdate")
	public ResponseEntity<Object> createOrUpdateUser(
			@io.swagger.v3.oas.annotations.parameters.RequestBody ( description = "User oject")
			@Valid @RequestBody EndUser endUser ){	
		return endUserService.createOrUpdateUser(endUser);
	}
	
	@Operation(summary = "Updates the user password", description = "updates user object with new password, password stored in encrypted format")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "404", description = "User with email xxx not found."),
	        @ApiResponse(responseCode = "200", description = "Password updated successfully."),
	        @ApiResponse(responseCode = "400", description = "Old password does not match."),
	    })
	@PutMapping("/updatePassword")
	public ResponseEntity<String> updatePassword(
			@io.swagger.v3.oas.annotations.parameters.RequestBody ( description = "Update password oject")
			@Valid @RequestBody UpdatePasswordPayload updatePasswordPayload ){
		return endUserService.updatePassword(updatePasswordPayload.getEmail(), 
				updatePasswordPayload.getOldPassword(), 
				updatePasswordPayload.getNewPassword());
	}
	
	@Operation(summary = "Deletes the user account", description = "deletes user object having given id as user object id.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "404", description = "User not found with id X."),
	        @ApiResponse(responseCode = "200", description = "User deleted successfully."),
	    })
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteUser( @Valid @Min(value=1,message = "end user id can not be zero or negative")@RequestParam Integer userId ){
		return endUserService.deleteUser(userId);
	}
	
	@Operation(summary = "Gets all user information", description = "Gets all user objects from end user table.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Request body as users object list or empty list."),
	    })
	@GetMapping("/getAll")
	public ResponseEntity<Object> getAllEndUser(){
		return endUserService.getAllEndUser();
	}
}
