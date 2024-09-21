package com.eshop.owneruser.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshop.owneruser.dto.EshopRegistrationPayload;
import com.eshop.owneruser.service.EshopOwnerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@CrossOrigin("*")
@RestController("EshopOwnerController")
@RequestMapping("/eshopOwner")
public class EshopOwnerController {

	@Autowired private EshopOwnerService eshopOwnerService;

	@Operation(summary = "Create Eshop user and eshop", description = "creates eshop and eshop user account")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "400", description = "Cover Image is mandatory to create e-shop."),
	        @ApiResponse(responseCode = "200", description = "response with all order details for given end user id"),
	        @ApiResponse(responseCode = "500", description = "Response body is null")
	    })
	@PostMapping("/createShopAndOwner")
	public ResponseEntity<Object> createEshopAndEshopOwner( @Valid EshopRegistrationPayload eshopRegistrationPayload ) throws IOException{
		return eshopOwnerService.createEshopAndEshopOwner(eshopRegistrationPayload);
	}
	
	@Operation(summary = "Gets cart total for end user", description = "")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "User deleted successfully."),
	        @ApiResponse(responseCode = "404", description = "User not found with id : x")
	    })
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteEshopAndEshopOwner( @Valid @Min(value=1,message = "Eshop user id can not be zero or negative") Integer userId){
		return eshopOwnerService.deleteUser(userId);
	}
	
	@Operation(summary = "Gets cart total for end user", description = "gets cart total for given end user id")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "response with all eshop owner details"),
	    })
	@GetMapping("/getAll")
	public ResponseEntity<Object> getAllEshopOwners(){
		return eshopOwnerService.getAllEshopOwners();
	}
}

