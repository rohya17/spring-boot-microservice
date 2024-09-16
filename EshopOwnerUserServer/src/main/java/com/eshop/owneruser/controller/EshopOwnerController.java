package com.eshop.owneruser.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eshop.owneruser.dto.EshopRegistrationPayload;
import com.eshop.owneruser.service.EshopOwnerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController("/eshopOwner")
public class EshopOwnerController {

	@Autowired private EshopOwnerService eshopOwnerService;

	@Operation(summary = "Gets cart total for end user", description = "gets cart total for given end user id")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "400", description = "Cover Image is mandatory to create e-shop."),
	        @ApiResponse(responseCode = "200", description = "response with all order details for given end user id"),
	        @ApiResponse(responseCode = "500", description = "Response body is null")
	    })
	@PostMapping("/createShopAndOwner")
	public ResponseEntity<Object> createEshopAndEshopOwner( @Valid EshopRegistrationPayload eshopRegistrationPayload ) throws IOException{
		return eshopOwnerService.createEshopAndEshopOwner(eshopRegistrationPayload);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteEshopAndEshopOwner( @RequestParam("userId") Integer userId){
		return eshopOwnerService.deleteUser(userId);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<Object> getAllEshopOwners(){
		return eshopOwnerService.getAllEshopOwners();
	}
}

