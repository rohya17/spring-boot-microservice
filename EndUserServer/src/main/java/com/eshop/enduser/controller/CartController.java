package com.eshop.enduser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eshop.enduser.models.CartItems;
import com.eshop.enduser.service.CartService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;


@CrossOrigin("*")
@RestController("CartController")
@RequestMapping("/cart")
public class CartController {

	@Autowired private CartService cartService;
	
	@Operation(summary = "Update the user cart with products", description = "Saves products as cart item for user")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Cart items saved successfully"), 
	        @ApiResponse(responseCode = "500", description = "Internal Sever Error - One or more product was not found")
	    })
	@PutMapping("/updateCart")
	public ResponseEntity<Object> updateCart(
			@Parameter(name = "endUserId", description = "End user object id", example = "1")
			@Valid @Min(value=1, message="end user id can not be zero") @RequestParam(required = true) Integer endUserId,
			@io.swagger.v3.oas.annotations.parameters.RequestBody ( description = "List of cart item ojects")
			@RequestBody List<CartItems> cartItems){
		return cartService.updateCart( endUserId, cartItems );
	}
	
}
